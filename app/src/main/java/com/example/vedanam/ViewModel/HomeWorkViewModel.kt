package com.example.vedanam.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vedanam.Model.ExamListInputModel
import com.example.vedanam.Model.HomeWorkOutputModel
import com.example.vedanam.Repository.HomeWorkRefreshError
import com.example.vedanam.Repository.HomeWorkRepository
import com.example.vedanam.Util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class HomeWorkViewModel (private val repository: HomeWorkRepository) : ViewModel() {

    val mutableHomeWorkLiveData = MutableLiveData<List<HomeWorkOutputModel>>()

    companion object {
        val FACTORY = singleArgViewModelFactory(::HomeWorkViewModel)
    }


    fun getHomeWorkList(request: ExamListInputModel) {
        viewModelScope.launch {
            try {
                mutableHomeWorkLiveData.value = repository.refreshHomeWork(request)
            } catch (error: HomeWorkRefreshError) {
            } finally {

            }

        }
    }
}