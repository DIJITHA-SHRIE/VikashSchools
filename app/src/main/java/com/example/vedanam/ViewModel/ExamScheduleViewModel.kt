package com.example.vedanam.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vedanam.Model.ExamScheduleOutputModel
import com.example.vedanam.Model.SectionListInputModel
import com.example.vedanam.Repository.ExamScheduleRefreshError
import com.example.vedanam.Repository.ExamScheduleRepository
import com.example.vedanam.Util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class ExamScheduleViewModel(private val repository: ExamScheduleRepository) : ViewModel() {
    val mutableExamScheduleLiveData = MutableLiveData<List<ExamScheduleOutputModel>>()

    companion object {
        val FACTORY = singleArgViewModelFactory(::ExamScheduleViewModel)
    }




    fun getExamSchedule(request: SectionListInputModel) {
        viewModelScope.launch {
            try {
                mutableExamScheduleLiveData.value = repository.refreshExamSchedule(request)
            } catch (error: ExamScheduleRefreshError) {
            } finally {

            }

        }
    }


}