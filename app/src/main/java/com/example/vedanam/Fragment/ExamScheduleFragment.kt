package com.example.vedanam.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vedanam.Adapter.ExamScheduleAdapter
import com.example.vedanam.Model.ExamScheduleOutputModel
import com.example.vedanam.Model.SectionListInputModel
import com.example.vedanam.R
import com.example.vedanam.Repository.ExamScheduleRepository
import com.example.vedanam.databinding.FragmentExamscheduleBinding
import com.example.vedanam.getNetworkService
import com.example.vedanam.ViewModel.ExamScheduleViewModel

class ExamScheduleFragment : Fragment() {

    private lateinit var examScheduleViewModel: ExamScheduleViewModel
    private lateinit var binding: FragmentExamscheduleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_examschedule, container, false)
        binding.setLifecycleOwner(this)

        val repository = ExamScheduleRepository(getNetworkService())
        val viewModel = ViewModelProviders
            .of(this,
                ExamScheduleViewModel.FACTORY(
                    repository
                )
            )
            .get(ExamScheduleViewModel::class.java)

        viewModel.getExamSchedule(SectionListInputModel("getExamSchedule","1"))

        viewModel.mutableExamScheduleLiveData.observe(this) { value ->
            value?.let {

                Log.i("ExamScheduleResponse",value.get(0).exam_name)
                val examSchedulelist:List<ExamScheduleOutputModel> = value

                try {
                    var adapter: ExamScheduleAdapter = ExamScheduleAdapter(examSchedulelist,activity!!)
                    binding.examScheduleRecycle!!.layoutManager = LinearLayoutManager(
                        activity,
                        LinearLayoutManager.VERTICAL, false
                    )
                    binding.examScheduleRecycle.adapter = adapter
                } catch (e: Exception) {
                    Log.i("examScheduleAdpExp",e.message)
                }


            }
        }

        return binding.root
    }
}