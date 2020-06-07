package com.example.vedanam.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vedanam.Model.ManageMarksOutputModel
import com.example.vedanam.R
import com.example.vedanam.databinding.ManageMarksDataLayoutBinding
import com.example.vedanam.databinding.ManageMarksHeaderBinding
import java.lang.Exception

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1



    class ManageMarksAdapter():ListAdapter<ManageMarksDataItem,RecyclerView.ViewHolder>(DiffUtilCallback()){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
                ITEM_VIEW_TYPE_ITEM -> ManageMarksViewHolder.from(parent)
                else -> throw ClassCastException("Unknown viewType ${viewType}")
            }



        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder) {
                is ManageMarksViewHolder -> {
                    val setManageMarksItem = getItem(position) as ManageMarksDataItem.ManageMarksItem
                    holder.bind(setManageMarksItem.manageMarksOutputModel)
                }
            }
        }

        override fun getItemViewType(position: Int): Int {
            return when (getItem(position)) {
                is ManageMarksDataItem.Header -> ITEM_VIEW_TYPE_HEADER
                is ManageMarksDataItem.ManageMarksItem -> ITEM_VIEW_TYPE_ITEM
                else -> throw Exception("Unknown View Type")
            }
        }

        fun addHeaderAndSubmitList(list:List<ManageMarksOutputModel>?){
            val items = when (list){
                null -> listOf(ManageMarksDataItem.Header)
                else -> listOf(ManageMarksDataItem.Header) + list.map{ManageMarksDataItem.ManageMarksItem(it)}
            }
            submitList(items)
        }

    }
    class ManageMarksViewHolder constructor(val binding: ManageMarksDataLayoutBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item:ManageMarksOutputModel){
            binding.mmMarks.text = item.mark_obtained
            binding.mmName.text = item.student_name
            binding.mmId.text = item.student_code


        }


        companion object {
            fun from(parent: ViewGroup): ManageMarksViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ManageMarksDataLayoutBinding.inflate(layoutInflater, parent, false)
                return ManageMarksViewHolder(binding)
            }
        }

    }
    class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.manage_marks_header, parent, false)
                return TextViewHolder(view)
            }
        }
    }

    class DiffUtilCallback:DiffUtil.ItemCallback<ManageMarksDataItem>(){
            override fun areItemsTheSame(oldItem: ManageMarksDataItem, newItem: ManageMarksDataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ManageMarksDataItem, newItem: ManageMarksDataItem): Boolean {
                return oldItem == newItem
            }

        }

    sealed class ManageMarksDataItem {
        abstract val id: Long
        data class ManageMarksItem(val manageMarksOutputModel: ManageMarksOutputModel): ManageMarksDataItem()      {
            override val id = manageMarksOutputModel.manageMarksId
        }

        object Header: ManageMarksDataItem() {
            override val id = Long.MIN_VALUE
        }
    }
