package com.example.vedanam.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vedanam.Model.SubjectInputModel
import com.example.vedanam.Model.SubjectOutputModel
import com.example.vedanam.Repository.SubjectRefreshError
import com.example.vedanam.Repository.SubjectRepository
import com.example.vedanam.Util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class SubjectViewmodel (private val repository: SubjectRepository) : ViewModel() {

    val mutableSubjectLiveData = MutableLiveData<List<SubjectOutputModel>>()

    companion object {
        val FACTORY = singleArgViewModelFactory(::SubjectViewmodel)
    }

    fun onGetStudentSubjects(request: SubjectInputModel) {
        getSubjectList(request)
    }


    fun getSubjectList(request: SubjectInputModel) {
        viewModelScope.launch {
            try {
                mutableSubjectLiveData.value = repository.refreshSubject(request)
            } catch (error: SubjectRefreshError) {
            } finally {

            }

        }
    }
}