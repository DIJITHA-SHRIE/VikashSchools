package com.example.vedanam.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vedanam.Model.LogInInputModel
import com.example.vedanam.Model.LogInModel
import com.example.vedanam.Repository.LogInRepository
import com.example.vedanam.Repository.LoginRefreshError
import com.example.vedanam.Util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class LogInViewModel(private val repository:LogInRepository):ViewModel() {

    val mutableLogInLiveData = MutableLiveData<LogInModel>()

    companion object {
        val FACTORY = singleArgViewModelFactory(::LogInViewModel)
    }

    fun onLogIn(request: LogInInputModel){
        viewModelScope.launch {
            try{
                mutableLogInLiveData.value = repository.getLogIn(request)
            }
            catch(error:LoginRefreshError){

            }
            finally {

            }
        }
    }




}