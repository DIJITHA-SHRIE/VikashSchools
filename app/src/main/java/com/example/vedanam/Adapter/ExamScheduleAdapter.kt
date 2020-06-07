package com.example.vedanam.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vedanam.Model.ExamScheduleOutputModel
import com.example.vedanam.Model.SubjectOutputModel
import com.example.vedanam.databinding.ExamscheduleAdapterLayoutBinding
import com.example.vedanam.databinding.SubjectAdapterLayoutBinding

class ExamScheduleAdapter (val items:List<ExamScheduleOutputModel>, val context: Context): RecyclerView.Adapter<ExamScheduleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamScheduleViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ExamscheduleAdapterLayoutBinding.inflate(inflater, parent, false)
        return ExamScheduleViewHolder(
            binding.root,
            binding
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ExamScheduleViewHolder, position: Int) {
        holder.bind(items.get(position))

    }


}
class ExamScheduleViewHolder constructor(itemView: View, binding: ExamscheduleAdapterLayoutBinding): RecyclerView.ViewHolder(itemView){

    private var mBinding: ExamscheduleAdapterLayoutBinding

    init {
        mBinding = binding
    }

    fun bind(getitems:ExamScheduleOutputModel){
        mBinding.esExam.text = "Exam: ${getitems.exam_name}"
        mBinding.esDate.text = "Date: ${getitems.date}"
        mBinding.esSubject.text = "Subject: ${getitems.subject_name}"
        mBinding.esSt.text="Start Time: ${getitems.start_time}.${getitems.start_min}${getitems.starting_ampm}"
        mBinding.esEt.text="End Time: ${getitems.end_time}.${getitems.end_min}${getitems.ending_ampm}"

    }

}