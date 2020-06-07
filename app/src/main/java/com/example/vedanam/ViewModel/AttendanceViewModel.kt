package com.example.vedanam.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vedanam.Model.AttendanceInputModel
import com.example.vedanam.Model.AttendanceModel
import com.example.vedanam.Repository.AttendanceRefreshError
import com.example.vedanam.Repository.AttendanceRepository
import com.example.vedanam.Util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class AttendanceViewModel(private val repository: AttendanceRepository) : ViewModel() {

     val mutableAttendanceLiveData = MutableLiveData<List<AttendanceModel>>()

    companion object {
        val FACTORY = singleArgViewModelFactory(::AttendanceViewModel)
    }

    fun onGetStudentAttendance(request: AttendanceInputModel){
        getAttendanceList(request)
    }



    fun getAttendanceList( request: AttendanceInputModel){
        viewModelScope.launch{
            try{
                mutableAttendanceLiveData.value = repository.refreshAttendance(request)
            }
            catch(error: AttendanceRefreshError){
            }
            finally {

            }

        }
    }


}