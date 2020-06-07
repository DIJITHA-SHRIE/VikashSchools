package com.example.vedanam.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vedanam.Model.*
import com.example.vedanam.Repository.ManageMarksRefreshError
import com.example.vedanam.Repository.ManageMarksRepository
import com.example.vedanam.Util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class ManageMarksViewModel(private val repository: ManageMarksRepository) : ViewModel() {

   val mutableManageMarksViewModel = MutableLiveData<List<ManageMarksOutputModel>>()
   val mutableExamListViewModel = MutableLiveData<List<ExamListOutputModel>>()
   val mutableClassListViewModel = MutableLiveData<List<ClassListOutputModel>>()
   val mutableSectionListViewModel = MutableLiveData<List<SectionListOutputModel>>()
   val mutableSubjectListViewModel = MutableLiveData<List<SubjectListOutputModel>>()


    companion object {
      val FACTORY = singleArgViewModelFactory(::ManageMarksViewModel)
    }

    fun onGetManageMarks(request: ManageMarksInputmodel){
        viewModelScope.launch {
            try {
                mutableManageMarksViewModel.value = repository.refreshManageMarks(request)
            } catch (error: ManageMarksRefreshError) {
            } finally {

            }

        }
    }
    fun onGetExamList(request: ExamListInputModel){
        viewModelScope.launch {
            try {
                mutableExamListViewModel.value = repository.refreshExamList(request)
            } catch (error: ManageMarksRefreshError) {
            } finally {

            }

        }
    }
    fun onGetClassList(request: ExamListInputModel){
        viewModelScope.launch {
            try {
                mutableClassListViewModel.value = repository.refreshClasslist(request)
            } catch (error: ManageMarksRefreshError) {
            } finally {

            }

        }
    }
    fun onGetSectionList(request: SectionListInputModel){
        viewModelScope.launch {
            try {
                mutableSectionListViewModel.value = repository.refreshSectionList(request)
            } catch (error: ManageMarksRefreshError) {
            } finally {

            }

        }
    }
    fun onGetSubjectList(request: SubjectInputModel){
        viewModelScope.launch {
            try {
                mutableSubjectListViewModel.value = repository.refreshSubjectList(request)
            } catch (error: ManageMarksRefreshError) {
            } finally {

            }

        }
    }



}