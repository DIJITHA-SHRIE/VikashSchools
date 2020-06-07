package com.example.vedanam.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vedanam.Model.ExamListInputModel
import com.example.vedanam.Model.NoticeOutputModel
import com.example.vedanam.Repository.NoticeBoardRepository
import com.example.vedanam.Repository.NoticeRefreshError
import com.example.vedanam.Util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class NoticeBoardViewModel (private val repository: NoticeBoardRepository) : ViewModel() {

    val mutableNoticeBoardLiveData = MutableLiveData<List<NoticeOutputModel>>()

    companion object {
        val FACTORY = singleArgViewModelFactory(::NoticeBoardViewModel)
    }




    fun getNoticetList(request: ExamListInputModel) {
        viewModelScope.launch {
            try {
                mutableNoticeBoardLiveData.value = repository.refreshNotieBoard(request)
            } catch (error: NoticeRefreshError) {
            } finally {

            }

        }
    }
}