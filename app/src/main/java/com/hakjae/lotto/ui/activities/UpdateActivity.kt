package com.hakjae.lotto.ui.activities

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hakjae.lotto.R
import com.hakjae.lotto.data.Lotto
import com.hakjae.lotto.data.Preview
import com.hakjae.lotto.databinding.ActivityAddBinding
import com.hakjae.lotto.databinding.DialogPreviewBinding
import com.hakjae.lotto.databinding.DialogProgressBinding
import com.hakjae.lotto.lib.filter.MinMaxFilter
import com.hakjae.lotto.lib.room.AppDatabase
import com.hakjae.lotto.ui.adapters.PreviewListAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var progressDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "복권 수정"
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

        val data = intent.extras?.getSerializable("data") as Lotto
        binding.roundEditText.setText(data.getRound().toString())

        binding.AOne.setText(data.getAOne().toString())
        binding.ATwo.setText(data.getATwo().toString())
        binding.AThree.setText(data.getAThree().toString())
        binding.AFour.setText(data.getAFour().toString())
        binding.AFive.setText(data.getAFive().toString())
        binding.ASix.setText(data.getASix().toString())
        if (data.getBOne() != null) {
            binding.BOne.setText(data.getBOne().toString())
            binding.BTwo.setText(data.getBTwo().toString())
            binding.BThree.setText(data.getBThree().toString())
            binding.BFour.setText(data.getBFour().toString())
            binding.BFive.setText(data.getBFive().toString())
            binding.BSix.setText(data.getBSix().toString())
        }
        if (data.getCOne() != null) {
            binding.COne.setText(data.getCOne().toString())
            binding.CTwo.setText(data.getCTwo().toString())
            binding.CThree.setText(data.getCThree().toString())
            binding.CFour.setText(data.getCFour().toString())
            binding.CFive.setText(data.getCFive().toString())
            binding.CSix.setText(data.getCSix().toString())
        }
        if (data.getDOne() != null) {
            binding.DOne.setText(data.getDOne().toString())
            binding.DTwo.setText(data.getDTwo().toString())
            binding.DThree.setText(data.getDThree().toString())
            binding.DFour.setText(data.getDFour().toString())
            binding.DFive.setText(data.getDFive().toString())
            binding.DSix.setText(data.getDSix().toString())
        }
        if (data.getEOne() != null) {
            binding.EOne.setText(data.getEOne().toString())
            binding.ETwo.setText(data.getETwo().toString())
            binding.EThree.setText(data.getEThree().toString())
            binding.EFour.setText(data.getEFour().toString())
            binding.EFive.setText(data.getEFive().toString())
            binding.ESix.setText(data.getESix().toString())
        }

        binding.saveButton.setOnClickListener {
            val round = binding.roundEditText.text.toString()
            if (!checkRoundInput(round)) return@setOnClickListener
            val writed = getLottoInputArray()
            if (writed.size == 0) {
                Toast.makeText(applicationContext, "값을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dialog = DialogPreviewBinding.inflate(LayoutInflater.from(applicationContext))
            val previewListAdapter = PreviewListAdapter(this)
            for (i in writed) {
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
                .setTitle(String.format("%d회차 복권 수정", round.toInt()))
                .setView(dialog.root)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    addLottoDatabase(data.getIdx()!!, round.toInt(), writed)
                }
                .setNegativeButton(android.R.string.cancel) { _, _ ->
                }
                .show()
        }
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

    private fun addLottoDatabase(idx: Int, round: Int, list: ArrayList<Array<Int>>) {
        progressDialog.show()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "lotto"
        ).build()

        val date =
            SimpleDateFormat("yyyy.MM.dd.", Locale.KOREA).format(Date(System.currentTimeMillis()))

        var aOne: Int? = null
        var aTwo: Int? = null
        var aThree: Int? = null
        var aFour: Int? = null
        var aFive: Int? = null
        var aSix: Int? = null
        var bOne: Int? = null
        var bTwo: Int? = null
        var bThree: Int? = null
        var bFour: Int? = null
        var bFive: Int? = null
        var bSix: Int? = null
        var cOne: Int? = null
        var cTwo: Int? = null
        var cThree: Int? = null
        var cFour: Int? = null
        var cFive: Int? = null
        var cSix: Int? = null
        var dOne: Int? = null
        var dTwo: Int? = null
        var dThree: Int? = null
        var dFour: Int? = null
        var dFive: Int? = null
        var dSix: Int? = null
        var eOne: Int? = null
        var eTwo: Int? = null
        var eThree: Int? = null
        var eFour: Int? = null
        var eFive: Int? = null
        var eSix: Int? = null

        if (list.size >= 1) {
            aOne =list[0][0]
            aTwo =list[0][1]
            aThree =list[0][2]
            aFour =list[0][3]
            aFive =list[0][4]
            aSix =list[0][5]
        }
        if (list.size >= 2) {
            bOne =list[1][0]
            bTwo =list[1][1]
            bThree =list[1][2]
            bFour =list[1][3]
            bFive =list[1][4]
            bSix =list[1][5]
        }
        if (list.size >= 3) {
            cOne =list[2][0]
            cTwo =list[2][1]
            cThree =list[2][2]
            cFour =list[2][3]
            cFive =list[2][4]
            cSix =list[2][5]
        }
        if (list.size >= 4) {
            dOne =list[3][0]
            dTwo =list[3][1]
            dThree =list[3][2]
            dFour =list[3][3]
            dFive =list[3][4]
            dSix =list[3][5]
        }
        if (list.size >= 5) {
            eOne =list[4][0]
            eTwo =list[4][1]
            eThree =list[4][2]
            eFour =list[4][3]
            eFive =list[4][4]
            eSix =list[4][5]
        }

        Thread {
            try {
                db.lottoDao().updateData(idx, round, aOne, aTwo, aThree, aFour, aFive, aSix, bOne, bTwo, bThree, bFour, bFive, bSix, cOne, cTwo, cThree, cFour, cFive, cSix, dOne, dTwo, dThree, dFour, dFive, dSix, eOne, eTwo, eThree, eFour, eFive, eSix, date)
                progressDialog.dismiss()
                runOnUiThread {
                    Toast.makeText(applicationContext, "변경되었습니다.", Toast.LENGTH_SHORT).show()
                }
                finish()
            } catch (e: Exception) {
            }
        }.start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}