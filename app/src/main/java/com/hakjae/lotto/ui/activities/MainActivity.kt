package com.hakjae.lotto.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.zxing.integration.android.IntentIntegrator
import com.hakjae.lotto.R
import com.hakjae.lotto.databinding.ActivityMainBinding
import com.hakjae.lotto.databinding.DialogInfoBinding
import com.hakjae.lotto.databinding.DialogPreviewBinding
import com.hakjae.lotto.databinding.DialogResultBinding
import com.hakjae.lotto.http.DataRequestClient
import com.hakjae.lotto.http.Result
import com.hakjae.lotto.lib.room.AppDatabase
import com.hakjae.lotto.ui.adapters.LottoListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var lottoListAdapter: LottoListAdapter

    private var maxCount: Int = 5
    private var getDataCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "로또6/45"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24dp)
        binding.toolbar.overflowIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_baseline_more_vert_24dp);

        lottoListAdapter = LottoListAdapter(this)
        binding.lottoRecyclerView.adapter = lottoListAdapter
        lottoListAdapter.setOnResultClickListener {
            getLottoData(it)
        }
        lottoListAdapter.setOnSettingClickListener {
            MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                .setItems(R.array.setting_lotto) { _, which ->
                    when (which) {
                        0 -> {
                            val intent = Intent(this, UpdateActivity::class.java)
                            intent.putExtra("data", lottoListAdapter.getData(it))
                            startActivity(intent)
                        }
                        1 -> {
                            MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                                .setMessage("해당 회차를 삭제합니다.")
                                .setPositiveButton(android.R.string.ok) { _, _ ->
                                    val db = Room.databaseBuilder(
                                        applicationContext,
                                        AppDatabase::class.java, "lotto"
                                    ).build()
                                    val idx = lottoListAdapter.getIndex(it)!!
                                    Thread {
                                        db.lottoDao().deleteData(idx)
                                        runOnUiThread {
                                            lottoListAdapter.remove(it)
                                            lottoListAdapter.notifyDataSetChanged()
                                            if (lottoListAdapter.itemCount == 0) {
                                                binding.addLayout.visibility = View.VISIBLE
                                                binding.lottoRecyclerView.visibility = View.GONE
                                            }
                                        }
                                    }.start()
                                }
                                .setNegativeButton(android.R.string.cancel) { _, _ ->
                                }
                                .show()
                        }
                    }
                }
                .show()
        }

        binding.addQrCode.setOnClickListener {
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setBeepEnabled(false)
            intentIntegrator.captureActivity = ReaderActivity::class.java
            intentIntegrator.initiateScan()
        }

        binding.addTyping.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        binding.lottoRecyclerView.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!binding.lottoRecyclerView.canScrollVertically(1)) {
                    getMoreLotto()
                }
            }
        })

        binding.homeButton.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }

        binding.infoButton.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            val dialog = DialogInfoBinding.inflate(LayoutInflater.from(applicationContext))
            MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                .setView(dialog.root)
                .show()
        }

        binding.settingButton.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            finish()
        }
    }

    private fun getLottoList() {
        setProgressVisible(true)
        lottoListAdapter.clear()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "lotto"
        ).build()

        Thread {
            val data = db.lottoDao().getLottoData(0, maxCount)
            getDataCount = data.size
            Log.e("lotto-count", "$maxCount $getDataCount")
            for (i in data) {
                val lotto = com.hakjae.lotto.data.Lotto()
                lotto.setIdx(i.idx)
                lotto.setRound(i.round)
                lotto.setDate(i.date)
                lotto.setA(i.A_one, i.A_two, i.A_three, i.A_four, i.A_five, i.A_six)
                if (i.B_one != null)
                    lotto.setB(i.B_one, i.B_two!!, i.B_three!!, i.B_four!!, i.B_five!!, i.B_six!!)
                if (i.C_one != null)
                    lotto.setC(i.C_one, i.C_two!!, i.C_three!!, i.C_four!!, i.C_five!!, i.C_six!!)
                if (i.D_one != null)
                    lotto.setD(i.D_one, i.D_two!!, i.D_three!!, i.D_four!!, i.D_five!!, i.D_six!!)
                if (i.E_one != null)
                    lotto.setE(i.E_one, i.E_two!!, i.E_three!!, i.E_four!!, i.E_five!!, i.E_six!!)
                lottoListAdapter.add(lotto)
            }
            runOnUiThread {
                lottoListAdapter.notifyDataSetChanged()
                setProgressVisible(false)
                if (lottoListAdapter.itemCount == 0) {
                    binding.addLayout.visibility = View.VISIBLE
                    binding.lottoRecyclerView.visibility = View.GONE
                } else {
                    binding.addLayout.visibility = View.GONE
                    binding.lottoRecyclerView.visibility = View.VISIBLE
                }
            }
        }.start()
    }

    private fun setLottoDeleteAll() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "lotto"
        ).build()

        Thread {
            db.lottoDao().deleteAll()
            runOnUiThread {
                getLottoList()
            }
        }.start()
    }

    private fun getMoreLotto() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "lotto"
        ).build()
        Thread {
            val count = db.lottoDao().getCount()
            runOnUiThread {
                if (maxCount < count) {
                    maxCount += 5
                    getLottoList()
                }
            }
        }.start()
    }

    private fun setProgressVisible(visible: Boolean) {
        binding.progressView.let {
            it.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun checkLottoUrl(url: String): Boolean {
        return url.startsWith("http://m.dhlottery.co.kr/")
    }

    private fun getLottoData(position: Int) {
        val round = lottoListAdapter.getRound(position).toString()
        DataRequestClient.getLottoDataService().getLottoDataApi(round)
            .enqueue(object : Callback<Result> {
                override fun onFailure(call: Call<Result>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "네트워크 상태를 확인해주세요",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.e("retrofit2", t.toString())
                }

                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    if (response.isSuccessful) {
                        if (response.body()?.getReturnValue() == "fail") {
                            Toast.makeText(
                                applicationContext,
                                "회당 회차는 아직 추첨되지 않았습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val body = response.body()!!
                            lottoListAdapter.setResultData(body)
                            lottoListAdapter.notifyItemChanged(position)

                            val dialog =
                                DialogResultBinding.inflate(LayoutInflater.from(applicationContext))
                            dialog.one.text = body.getDrwtNo1()!!.toString()
                            dialog.two.text = body.getDrwtNo2()!!.toString()
                            dialog.three.text = body.getDrwtNo3()!!.toString()
                            dialog.four.text = body.getDrwtNo4()!!.toString()
                            dialog.five.text = body.getDrwtNo5()!!.toString()
                            dialog.six.text = body.getDrwtNo6()!!.toString()
                            dialog.bonus.text = body.getBnusNo()!!.toString()

                            setBackgroundColor(dialog.one, body.getDrwtNo1()!!)
                            setBackgroundColor(dialog.two, body.getDrwtNo2()!!)
                            setBackgroundColor(dialog.three, body.getDrwtNo3()!!)
                            setBackgroundColor(dialog.four, body.getDrwtNo4()!!)
                            setBackgroundColor(dialog.five, body.getDrwtNo5()!!)
                            setBackgroundColor(dialog.six, body.getDrwtNo6()!!)
                            setBackgroundColor(dialog.bonus, body.getBnusNo()!!)

                            MaterialAlertDialogBuilder(this@MainActivity, R.style.AlertDialogTheme)
                                .setTitle(String.format("%s회차 결과", round))
                                .setView(dialog.root)
                                .setPositiveButton("닫기") { _, _ ->
                                }
                                .show()
                        }
                    }
                }
            })
    }

    private fun setBackgroundColor(tv: TextView, value: Int) {
        when (value) {
            in 1..10 -> tv.setBackgroundResource(R.drawable.oval_yellow)
            in 11..20 -> tv.setBackgroundResource(R.drawable.oval_blue)
            in 21..30 -> tv.setBackgroundResource(R.drawable.oval_red)
            in 31..40 -> tv.setBackgroundResource(R.drawable.oval_grey)
            in 41..45 -> tv.setBackgroundResource(R.drawable.oval_green)
        }
    }

    override fun onResume() {
        super.onResume()
        getLottoList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
            R.id.add -> {
                MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                    .setItems(R.array.add_lotto) { _, which ->
                        when (which) {
                            0 -> {
                                val intentIntegrator = IntentIntegrator(this)
                                intentIntegrator.setBeepEnabled(false)
                                intentIntegrator.captureActivity = ReaderActivity::class.java
                                intentIntegrator.initiateScan()
                            }
                            1 -> {
                                startActivity(Intent(this, AddActivity::class.java))
                            }
                        }
                    }
                    .show()
            }
            R.id.remove -> {
                MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                    .setMessage("등록된 정보를 모두 삭제합니다.")
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        setLottoDeleteAll()
                    }
                    .setNegativeButton(android.R.string.cancel) { _, _ ->
                    }
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                if (checkLottoUrl(result.contents)) {
                    val intent = Intent(this, AddActivity::class.java)
                    intent.putExtra("url", result.contents)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "링크가 유효하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}