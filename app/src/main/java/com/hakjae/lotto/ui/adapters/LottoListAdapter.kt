package com.hakjae.lotto.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hakjae.lotto.R
import com.hakjae.lotto.data.Lotto
import com.hakjae.lotto.databinding.ItemLottoBinding
import com.hakjae.lotto.http.Result

class LottoListAdapter(private var context: Context) :
    RecyclerView.Adapter<LottoListAdapter.LottoListViewHolder>() {

    private var lottos = ArrayList<Lotto>()
    private var result = ArrayList<Result>()
    private var onResultClickListener: ((Int) -> Unit)? = null
    private var onSettingClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LottoListViewHolder {
        return LottoListViewHolder(
            ItemLottoBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    fun setResultData(r: Result) {
        if (result.indexOf(r) == -1) result.add(r)
    }

    fun setOnResultClickListener(f: (Int) -> Unit) {
        onResultClickListener = f
    }

    fun setOnSettingClickListener(f: (Int) -> Unit) {
        onSettingClickListener = f
    }

    override fun getItemCount(): Int {
        return lottos.size
    }

    override fun onBindViewHolder(holder: LottoListViewHolder, position: Int) {
        holder.bind(lottos[position])
    }

    fun add(lotto: Lotto) {
        lottos.add(lotto)
    }

    fun clear() {
        lottos.clear()
    }

    fun getRound(position: Int): Int? {
        return lottos[position].getRound()
    }

    fun getIndex(position: Int): Int? {
        return lottos[position].getIdx()
    }

    fun remove(position: Int) {
        lottos.removeAt(position)
    }

    fun getData(position: Int): Lotto {
        return lottos[position]
    }

    inner class LottoListViewHolder(private val binding: ItemLottoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(lotto: Lotto) {
            binding.roundText.text = String.format("%d회차", lotto.getRound())
            binding.dateText.text = String.format("추가 날짜 %s", lotto.getDate())

            binding.AOne.text = lotto.getAOne().toString()
            setBackgroundColor(binding.AOne, lotto.getAOne()!!)
            binding.ATwo.text = lotto.getATwo().toString()
            setBackgroundColor(binding.ATwo, lotto.getATwo()!!)
            binding.AThree.text = lotto.getAThree().toString()
            setBackgroundColor(binding.AThree, lotto.getAThree()!!)
            binding.AFour.text = lotto.getAFour().toString()
            setBackgroundColor(binding.AFour, lotto.getAFour()!!)
            binding.AFive.text = lotto.getAFive().toString()
            setBackgroundColor(binding.AFive, lotto.getAFive()!!)
            binding.ASix.text = lotto.getASix().toString()
            setBackgroundColor(binding.ASix, lotto.getASix()!!)

            if (lotto.getBOne() != null) {
                binding.BLayout.visibility = View.VISIBLE
                binding.BOne.text = lotto.getBOne().toString()
                setBackgroundColor(binding.BOne, lotto.getBOne()!!)
                binding.BTwo.text = lotto.getBTwo().toString()
                setBackgroundColor(binding.BTwo, lotto.getBTwo()!!)
                binding.BThree.text = lotto.getBThree().toString()
                setBackgroundColor(binding.BThree, lotto.getBThree()!!)
                binding.BFour.text = lotto.getBFour().toString()
                setBackgroundColor(binding.BFour, lotto.getBFour()!!)
                binding.BFive.text = lotto.getBFive().toString()
                setBackgroundColor(binding.BFive, lotto.getBFive()!!)
                binding.BSix.text = lotto.getBSix().toString()
                setBackgroundColor(binding.BSix, lotto.getBSix()!!)
            } else {
                binding.BLayout.visibility = View.GONE
            }

            if (lotto.getCOne() != null) {
                binding.CLayout.visibility = View.VISIBLE
                binding.COne.text = lotto.getCOne().toString()
                setBackgroundColor(binding.COne, lotto.getCOne()!!)
                binding.CTwo.text = lotto.getCTwo().toString()
                setBackgroundColor(binding.CTwo, lotto.getCTwo()!!)
                binding.CThree.text = lotto.getCThree().toString()
                setBackgroundColor(binding.CThree, lotto.getCThree()!!)
                binding.CFour.text = lotto.getCFour().toString()
                setBackgroundColor(binding.CFour, lotto.getCFour()!!)
                binding.CFive.text = lotto.getCFive().toString()
                setBackgroundColor(binding.CFive, lotto.getCFive()!!)
                binding.CSix.text = lotto.getCSix().toString()
                setBackgroundColor(binding.CSix, lotto.getCSix()!!)
            } else {
                binding.CLayout.visibility = View.GONE
            }

            if (lotto.getDOne() != null) {
                binding.DLayout.visibility = View.VISIBLE
                binding.DOne.text = lotto.getDOne().toString()
                setBackgroundColor(binding.DOne, lotto.getDOne()!!)
                binding.DTwo.text = lotto.getDTwo().toString()
                setBackgroundColor(binding.DTwo, lotto.getDTwo()!!)
                binding.DThree.text = lotto.getDThree().toString()
                setBackgroundColor(binding.DThree, lotto.getDThree()!!)
                binding.DFour.text = lotto.getDFour().toString()
                setBackgroundColor(binding.DFour, lotto.getDFour()!!)
                binding.DFive.text = lotto.getDFive().toString()
                setBackgroundColor(binding.DFive, lotto.getDFive()!!)
                binding.DSix.text = lotto.getDSix().toString()
                setBackgroundColor(binding.DSix, lotto.getDSix()!!)
            } else {
                binding.DLayout.visibility = View.GONE
            }

            if (lotto.getEOne() != null) {
                binding.ELayout.visibility = View.VISIBLE
                binding.EOne.text = lotto.getEOne().toString()
                setBackgroundColor(binding.EOne, lotto.getEOne()!!)
                binding.ETwo.text = lotto.getETwo().toString()
                setBackgroundColor(binding.ETwo, lotto.getETwo()!!)
                binding.EThree.text = lotto.getEThree().toString()
                setBackgroundColor(binding.EThree, lotto.getEThree()!!)
                binding.EFour.text = lotto.getEFour().toString()
                setBackgroundColor(binding.EFour, lotto.getEFour()!!)
                binding.EFive.text = lotto.getEFive().toString()
                setBackgroundColor(binding.EFive, lotto.getEFive()!!)
                binding.ESix.text = lotto.getESix().toString()
                setBackgroundColor(binding.ESix, lotto.getESix()!!)
            } else {
                binding.ELayout.visibility = View.GONE
            }

            binding.resultButton.setOnClickListener {
                onResultClickListener?.invoke(adapterPosition)
            }

            binding.settingButton.setOnClickListener {
                onSettingClickListener?.invoke(adapterPosition)
            }

            var count = 0
            for (i in result) {
                if (i.getDrwNo() == lotto.getRound()) {
                    count++
                    val data = listOf(
                        i.getDrwtNo1()!!, i.getDrwtNo2()!!, i.getDrwtNo3()!!,
                        i.getDrwtNo4()!!, i.getDrwtNo5()!!, i.getDrwtNo6()!!
                    )
                    val aData = listOf(
                        lotto.getAOne()!!, lotto.getATwo()!!, lotto.getAThree()!!,
                        lotto.getAFour()!!, lotto.getAFive()!!, lotto.getASix()!!
                    )
                    binding.AResult.text = getResult(data, aData, i.getBnusNo()!!)
                    if (lotto.getBOne() != null) {
                        val bData = listOf(
                            lotto.getBOne()!!, lotto.getBTwo()!!, lotto.getBThree()!!,
                            lotto.getBFour()!!, lotto.getBFive()!!, lotto.getBSix()!!
                        )
                        binding.BResult.text = getResult(data, bData, i.getBnusNo()!!)
                    }
                    if (lotto.getCOne() != null) {
                        val cData = listOf(
                            lotto.getCOne()!!, lotto.getCTwo()!!, lotto.getCThree()!!,
                            lotto.getCFour()!!, lotto.getCFive()!!, lotto.getCSix()!!
                        )
                        binding.CResult.text = getResult(data, cData, i.getBnusNo()!!)
                    }
                    if (lotto.getDOne() != null) {
                        val dData = listOf(
                            lotto.getDOne()!!, lotto.getDTwo()!!, lotto.getDThree()!!,
                            lotto.getDFour()!!, lotto.getDFive()!!, lotto.getDSix()!!
                        )
                        binding.DResult.text = getResult(data, dData, i.getBnusNo()!!)
                    }
                    if (lotto.getEOne() != null) {
                        val eData = listOf(
                            lotto.getEOne()!!, lotto.getETwo()!!, lotto.getEThree()!!,
                            lotto.getEFour()!!, lotto.getEFive()!!, lotto.getESix()!!
                        )
                        binding.EResult.text = getResult(data, eData, i.getBnusNo()!!)
                    }
                }
            }
            if (count == 0) {
                binding.AResult.text = "?"
                if (lotto.getBOne() != null) binding.BResult.text = "?"
                if (lotto.getCOne() != null) binding.CResult.text = "?"
                if (lotto.getDOne() != null) binding.DResult.text = "?"
                if (lotto.getEOne() != null) binding.EResult.text = "?"
            }
        }

        private fun getResult(data: List<Int>, user: List<Int>, bonus: Int): String {
            var count = 0
            for (i in user) {
                if (data.contains(i)) count++
            }
            when (count) {
                6 -> return "1등"
                5 -> return "3등"
                4 -> return "4등"
                3 -> return "5등"
            }
            return if (count == 5 && user.contains(bonus)) "2등"
            else "X"
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
    }
}