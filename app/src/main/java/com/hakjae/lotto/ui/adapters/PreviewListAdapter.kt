package com.hakjae.lotto.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hakjae.lotto.data.Preview
import com.hakjae.lotto.databinding.ItemPreviewBinding

class PreviewListAdapter(private var context: Context) :
    RecyclerView.Adapter<PreviewListAdapter.PreviewListViewHolder>() {

    private var previews = ArrayList<Preview>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewListViewHolder {
        return PreviewListViewHolder(
            ItemPreviewBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return previews.size
    }

    override fun onBindViewHolder(holder: PreviewListViewHolder, position: Int) {
        holder.bind(previews[position])
    }

    fun add(preview: Preview) {
        previews.add(preview)
    }

    fun clear() {
        previews.clear()
    }

    inner class PreviewListViewHolder(private val binding: ItemPreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(preview: Preview) {
            binding.one.text = preview.getOne()
            binding.two.text = preview.getTwo()
            binding.three.text = preview.getThree()
            binding.four.text = preview.getFour()
            binding.five.text = preview.getFive()
            binding.six.text = preview.getSix()
        }
    }
}