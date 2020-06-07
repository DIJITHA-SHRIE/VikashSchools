package com.example.vedanam.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vedanam.Model.SubjectOutputModel
import com.example.vedanam.databinding.SubjectAdapterLayoutBinding

class SubjectAdapter(val items:List<SubjectOutputModel>, val context:Context):RecyclerView.Adapter<DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = SubjectAdapterLayoutBinding.inflate(inflater, parent, false)
        return DataViewHolder(
            binding.root,
            binding
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(items.get(position).class_name,items.get(position).subject_name,items.get(position).teacher_name)

    }


}
class DataViewHolder constructor(itemView: View, binding: SubjectAdapterLayoutBinding):RecyclerView.ViewHolder(itemView){

    private var mBinding: SubjectAdapterLayoutBinding

    init {
        mBinding = binding
    }

    fun bind(className:String?, subjectName:String?, teacherName:String ){
         mBinding.subClass.text = className
         mBinding.subSub.text = subjectName
         mBinding.subTeacher.text = teacherName

    }

}