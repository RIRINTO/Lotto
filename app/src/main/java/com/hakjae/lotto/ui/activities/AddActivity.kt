package com.hakjae.lotto.ui.activities

import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hakjae.lotto.R
import com.hakjae.lotto.data.Preview
import com.hakjae.lotto.databinding.ActivityAddBinding
import com.hakjae.lotto.databinding.DialogPreviewBinding
import com.hakjae.lotto.databinding.DialogProgressBinding
import com.hakjae.lotto.lib.filter.MinMaxFilter
import com.hakjae.lotto.lib.room.AppDatabase
import com.hakjae.lotto.lib.room.Lotto
import com.hakjae.lotto.ui.adapters.PreviewListAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet


class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var progressDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "복권 추가"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24dp)

        val progress = DialogProgressBinding.inflate(LayoutInflater.from(applicationContext))
        val progressBuilder = MaterialAlertDialogBuilder(this)
            .setView(progress.root)
        progressDialog = progressBuilder.create()
        progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        progressDialog.setCancelable(false)

        binding.AOne.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.ATwo.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.AThree.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.AFour.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.AFive.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.ASix.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))

        binding.BOne.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.BTwo.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.BThree.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.BFour.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.BFive.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.BSix.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))

        binding.COne.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.CTwo.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.CThree.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.CFour.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.CFive.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.CSix.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))

        binding.DOne.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.DTwo.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.DThree.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.DFour.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.DFive.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.DSix.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))

        binding.EOne.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.ETwo.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.EThree.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.EFour.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.EFive.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))
        binding.ESix.filters = arrayOf<InputFilter>(MinMaxFilter("1", "45"))

        val url = intent.extras?.getString("url")
        if (url != null) {
            Log.e("lotto-url", url)
            val urlData = url.replace("http://m.dhlottery.co.kr/?v=", "").split("q")
            Log.e("lotto-array", urlData.toString())

            for (i in urlData.indices) {
                if (i == 0) binding.roundEditText.setText(urlData[i])
                else {
                    val array = setSplitLottoData(urlData[i])
                    when (i) {
                        1 -> {
                            binding.AOne.setText(array[0])
                            binding.ATwo.setText(array[1])
                            binding.AThree.setText(array[2])
                            binding.AFour.setText(array[3])
                            binding.AFive.setText(array[4])
                            binding.ASix.setText(array[5])
                        }
                        2 -> {
                            binding.BOne.setText(array[0])
                            binding.BTwo.setText(array[1])
                            binding.BThree.setText(array[2])
                            binding.BFour.setText(array[3])
                            binding.BFive.setText(array[4])
                            binding.BSix.setText(array[5])
                        }
                        3 -> {
                            binding.COne.setText(array[0])
                            binding.CTwo.setText(array[1])
                            binding.CThree.setText(array[2])
                            binding.CFour.setText(array[3])
                            binding.CFive.setText(array[4])
                            binding.CSix.setText(array[5])
                        }
                        4 -> {
                            binding.DOne.setText(array[0])
                            binding.DTwo.setText(array[1])
                            binding.DThree.setText(array[2])
                            binding.DFour.setText(array[3])
                            binding.DFive.setText(array[4])
                            binding.DSix.setText(array[5])
                        }
                        5 -> {
                            binding.EOne.setText(array[0])
                            binding.ETwo.setText(array[1])
                            binding.EThree.setText(array[2])
                            binding.EFour.setText(array[3])
                            binding.EFive.setText(array[4])
                            binding.ESix.setText(array[5])
                        }
                    }
                }
            }
        }

        binding.saveButton.setOnClickListener {
            val round = binding.roundEditText.text.toString()
            if (!checkRoundInput(round)) return@setOnClickListener
            val data = getLottoInputArray()
            if (data.size == 0) {
                Toast.makeText(applicationContext, "값을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dialog = DialogPreviewBinding.inflate(LayoutInflater.from(applicationContext))
            val previewListAdapter = PreviewListAdapter(this)
            for (i in data) {
                val preview = Preview(
                    i[0].toString(),
                    i[1].toString(),
                    i[2].toString(),
                    i[3].toString(),
                    i[4].toString(),
                    i[5].toString()
                )
                previewListAdapter.add(preview)
            }
            previewListAdapter.notifyDataSetChanged()
            dialog.previewRecyclerView.adapter = previewListAdapter

            MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                .setTitle(String.format("%d회차 복권 추가", round.toInt()))
                .setView(dialog.root)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    addLottoDatabase(round.toInt(), data)
                }
                .setNegativeButton(android.R.string.cancel) { _, _ ->
                }
                .show()
        }
    }

    private fun setSplitLottoData(number: String): Array<String?> {
        val array = arrayOfNulls<String>(6)
        array[0] = number.substring(0, 2)
        array[1] = number.substring(2, 4)
        array[2] = number.substring(4, 6)
        array[3] = number.substring(6, 8)
        array[4] = number.substring(8, 10)
        array[5] = number.substring(10, 12)
        return array
    }

    private fun checkRoundInput(round: String): Boolean {
        if (round.isEmpty()) Toast.makeText(
            applicationContext,
            "회차 정보를 입력해주세요.",
            Toast.LENGTH_SHORT
        ).show()
        return round.isNotEmpty()
    }

    private fun getLottoInputArray(): ArrayList<Array<Int>> {
        val list = ArrayList<Array<Int>>()
        if (binding.AOne.text.toString().isNotEmpty() &&
            binding.ATwo.text.toString().isNotEmpty() &&
            binding.AThree.text.toString().isNotEmpty() &&
            binding.AFour.text.toString().isNotEmpty() &&
            binding.AFive.text.toString().isNotEmpty() &&
            binding.ASix.text.toString().isNotEmpty()
        ) {
            val temp = arrayOf(
                binding.AOne.text.toString().toInt(),
                binding.ATwo.text.toString().toInt(),
                binding.AThree.text.toString().toInt(),
                binding.AFour.text.toString().toInt(),
                binding.AFive.text.toString().toInt(),
                binding.ASix.text.toString().toInt()
            )
            val set = hashSetOf(
                binding.AOne.text.toString().toInt(),
                binding.ATwo.text.toString().toInt(),
                binding.AThree.text.toString().toInt(),
                binding.AFour.text.toString().toInt(),
                binding.AFive.text.toString().toInt(),
                binding.ASix.text.toString().toInt()
            )
            if (temp.size != set.size) {
                Toast.makeText(applicationContext, "중복 값을 제거해주세요", Toast.LENGTH_SHORT).show()
            } else {
                Arrays.sort(temp)
                list.add(temp)
            }
        }

        if (binding.BOne.text.toString().isNotEmpty() &&
            binding.BTwo.text.toString().isNotEmpty() &&
            binding.BThree.text.toString().isNotEmpty() &&
            binding.BFour.text.toString().isNotEmpty() &&
            binding.BFive.text.toString().isNotEmpty() &&
            binding.BSix.text.toString().isNotEmpty()
        ) {
            val temp = arrayOf(
                binding.BOne.text.toString().toInt(),
                binding.BTwo.text.toString().toInt(),
                binding.BThree.text.toString().toInt(),
                binding.BFour.text.toString().toInt(),
                binding.BFive.text.toString().toInt(),
                binding.BSix.text.toString().toInt()
            )
            val set = hashSetOf(
                binding.BOne.text.toString().toInt(),
                binding.BTwo.text.toString().toInt(),
                binding.BThree.text.toString().toInt(),
                binding.BFour.text.toString().toInt(),
                binding.BFive.text.toString().toInt(),
                binding.BSix.text.toString().toInt()
            )
            if (temp.size != set.size) {
                Toast.makeText(applicationContext, "중복 값을 제거해주세요", Toast.LENGTH_SHORT).show()
            } else {
                Arrays.sort(temp)
                list.add(temp)
            }
        }

        if (binding.COne.text.toString().isNotEmpty() &&
            binding.CTwo.text.toString().isNotEmpty() &&
            binding.CThree.text.toString().isNotEmpty() &&
            binding.CFour.text.toString().isNotEmpty() &&
            binding.CFive.text.toString().isNotEmpty() &&
            binding.CSix.text.toString().isNotEmpty()
        ) {
            val temp = arrayOf(
                binding.COne.text.toString().toInt(),
                binding.CTwo.text.toString().toInt(),
                binding.CThree.text.toString().toInt(),
                binding.CFour.text.toString().toInt(),
                binding.CFive.text.toString().toInt(),
                binding.CSix.text.toString().toInt()
            )
            val set = hashSetOf(
                binding.COne.text.toString().toInt(),
                binding.CTwo.text.toString().toInt(),
                binding.CThree.text.toString().toInt(),
                binding.CFour.text.toString().toInt(),
                binding.CFive.text.toString().toInt(),
                binding.CSix.text.toString().toInt()
            )
            if (temp.size != set.size) {
                Toast.makeText(applicationContext, "중복 값을 제거해주세요", Toast.LENGTH_SHORT).show()
            } else {
                Arrays.sort(temp)
                list.add(temp)
            }
        }

        if (binding.DOne.text.toString().isNotEmpty() &&
            binding.DTwo.text.toString().isNotEmpty() &&
            binding.DThree.text.toString().isNotEmpty() &&
            binding.DFour.text.toString().isNotEmpty() &&
            binding.DFive.text.toString().isNotEmpty() &&
            binding.DSix.text.toString().isNotEmpty()
        ) {
            val temp = arrayOf(
                binding.DOne.text.toString().toInt(),
                binding.DTwo.text.toString().toInt(),
                binding.DThree.text.toString().toInt(),
                binding.DFour.text.toString().toInt(),
                binding.DFive.text.toString().toInt(),
                binding.DSix.text.toString().toInt()
            )
            val set = hashSetOf(
                binding.DOne.text.toString().toInt(),
                binding.DTwo.text.toString().toInt(),
                binding.DThree.text.toString().toInt(),
                binding.DFour.text.toString().toInt(),
                binding.DFive.text.toString().toInt(),
                binding.DSix.text.toString().toInt()
            )
            if (temp.size != set.size) {
                Toast.makeText(applicationContext, "중복 값을 제거해주세요", Toast.LENGTH_SHORT).show()
            } else {
                Arrays.sort(temp)
                list.add(temp)
            }
        }

        if (binding.EOne.text.toString().isNotEmpty() &&
            binding.ETwo.text.toString().isNotEmpty() &&
            binding.EThree.text.toString().isNotEmpty() &&
            binding.EFour.text.toString().isNotEmpty() &&
            binding.EFive.text.toString().isNotEmpty() &&
            binding.ESix.text.toString().isNotEmpty()
        ) {
            val temp = arrayOf(
                binding.EOne.text.toString().toInt(),
                binding.ETwo.text.toString().toInt(),
                binding.EThree.text.toString().toInt(),
                binding.EFour.text.toString().toInt(),
                binding.EFive.text.toString().toInt(),
                binding.ESix.text.toString().toInt()
            )
            val set = hashSetOf(
                binding.EOne.text.toString().toInt(),
                binding.ETwo.text.toString().toInt(),
                binding.EThree.text.toString().toInt(),
                binding.EFour.text.toString().toInt(),
                binding.EFive.text.toString().toInt(),
                binding.ESix.text.toString().toInt()
            )
            if (temp.size != set.size) {
                Toast.makeText(applicationContext, "중복 값을 제거해주세요", Toast.LENGTH_SHORT).show()
            } else {
                Arrays.sort(temp)
                list.add(temp)
            }
        }
        return list
    }

    private fun addLottoDatabase(round: Int, list: ArrayList<Array<Int>>) {
        progressDialog.show()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "lotto"
        ).build()

        val date =
            SimpleDateFormat("yyyy.MM.dd.", Locale.KOREA).format(Date(System.currentTimeMillis()))

        var lotto: Lotto? = null
        when (list.size) {
            1 -> lotto = Lotto(
                round = round,
                A_one = list[0][0],
                A_two = list[0][1],
                A_three = list[0][2],
                A_four = list[0][3],
                A_five = list[0][4],
                A_six = list[0][5],
                date = date
            )
            2 -> lotto = Lotto(
                round = round,
                A_one = list[0][0],
                A_two = list[0][1],
                A_three = list[0][2],
                A_four = list[0][3],
                A_five = list[0][4],
                A_six = list[0][5],
                B_one = list[1][0],
                B_two = list[1][1],
                B_three = list[1][2],
                B_four = list[1][3],
                B_five = list[1][4],
                B_six = list[1][5],
                date = date
            )
            3 -> lotto = Lotto(
                round = round,
                A_one = list[0][0],
                A_two = list[0][1],
                A_three = list[0][2],
                A_four = list[0][3],
                A_five = list[0][4],
                A_six = list[0][5],
                B_one = list[1][0],
                B_two = list[1][1],
                B_three = list[1][2],
                B_four = list[1][3],
                B_five = list[1][4],
                B_six = list[1][5],
                C_one = list[2][0],
                C_two = list[2][1],
                C_three = list[2][2],
                C_four = list[2][3],
                C_five = list[2][4],
                C_six = list[2][5],
                date = date
            )
            4 -> lotto = Lotto(
                round = round,
                A_one = list[0][0],
                A_two = list[0][1],
                A_three = list[0][2],
                A_four = list[0][3],
                A_five = list[0][4],
                A_six = list[0][5],
                B_one = list[1][0],
                B_two = list[1][1],
                B_three = list[1][2],
                B_four = list[1][3],
                B_five = list[1][4],
                B_six = list[1][5],
                C_one = list[2][0],
                C_two = list[2][1],
                C_three = list[2][2],
                C_four = list[2][3],
                C_five = list[2][4],
                C_six = list[2][5],
                D_one = list[2][0],
                D_two = list[2][1],
                D_three = list[2][2],
                D_four = list[2][3],
                D_five = list[2][4],
                D_six = list[3][5],
                date = date
            )
            5 -> lotto = Lotto(
                round = round,
                A_one = list[0][0],
                A_two = list[0][1],
                A_three = list[0][2],
                A_four = list[0][3],
                A_five = list[0][4],
                A_six = list[0][5],
                B_one = list[1][0],
                B_two = list[1][1],
                B_three = list[1][2],
                B_four = list[1][3],
                B_five = list[1][4],
                B_six = list[1][5],
                C_one = list[2][0],
                C_two = list[2][1],
                C_three = list[2][2],
                C_four = list[2][3],
                C_five = list[2][4],
                C_six = list[2][5],
                D_one = list[2][0],
                D_two = list[2][1],
                D_three = list[2][2],
                D_four = list[2][3],
                D_five = list[2][4],
                D_six = list[3][5],
                E_one = list[4][0],
                E_two = list[4][1],
                E_three = list[4][2],
                E_four = list[4][3],
                E_five = list[4][4],
                E_six = list[4][5],
                date = date
            )
        }

        Thread {
            try {
                db.lottoDao().insertAll(lotto!!)
                progressDialog.dismiss()
                runOnUiThread {
                    Toast.makeText(applicationContext, "저장되었습니다.", Toast.LENGTH_SHORT).show()
                }
                finish()
            } catch (e: Exception) {
            }
        }.start()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}