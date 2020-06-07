package com.example.vedanam.Adapter

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vedanam.Model.HomeWorkOutputModel
import com.example.vedanam.Model.SubjectOutputModel
import com.example.vedanam.databinding.ExamscheduleAdapterLayoutBinding
import com.example.vedanam.databinding.SubjectAdapterLayoutBinding

class HomeWorkAdapter (val items:List<HomeWorkOutputModel>, val context: Context):RecyclerView.Adapter<HomeWorkViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeWorkViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ExamscheduleAdapterLayoutBinding.inflate(inflater, parent, false)
        return HomeWorkViewHolder(
            binding.root,
            binding
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HomeWorkViewHolder, position: Int) {
        holder.bind(items.get(position))

    }


}
    class HomeWorkViewHolder constructor(itemView: View, binding: ExamscheduleAdapterLayoutBinding):
        RecyclerView.ViewHolder(itemView){

        private var mBinding: ExamscheduleAdapterLayoutBinding

        init {
            mBinding = binding
        }

        fun bind(getitems:HomeWorkOutputModel){
            mBinding.esExam.visibility = View.GONE
            mBinding.esDate.text = "Group: ${getitems.group_name}"
            mBinding.esSubject.text = "Topic: ${getitems.topic}"
            mBinding.esSt.text="Task: ${getitems.task}"
            mBinding.esEt.text="Date: ${getitems.created_at}"


        }

    }