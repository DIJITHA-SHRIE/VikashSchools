package com.example.vedanam.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vedanam.Model.HomeWorkOutputModel
import com.example.vedanam.Model.NoticeOutputModel
import com.example.vedanam.databinding.ExamscheduleAdapterLayoutBinding

class NoticeBoardAdapter (val items:List<NoticeOutputModel>, val context: Context): RecyclerView.Adapter<NoticeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ExamscheduleAdapterLayoutBinding.inflate(inflater, parent, false)
        return NoticeViewHolder(
            binding.root,
            binding
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(items.get(position))

    }


}
class NoticeViewHolder constructor(itemView: View, binding: ExamscheduleAdapterLayoutBinding):
    RecyclerView.ViewHolder(itemView){

    private var mBinding: ExamscheduleAdapterLayoutBinding

    init {
        mBinding = binding
    }

    fun bind(getitems: NoticeOutputModel){
        mBinding.esExam.visibility = View.GONE
        mBinding.esDate.visibility = View.GONE
        mBinding.esSubject.text = "Title: ${getitems.notice_title}"
        mBinding.esSt.text="Notice: ${getitems.notice}"
        mBinding.esEt.text="Date: ${getitems.create_timestamp}"


    }

}