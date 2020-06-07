package com.example.vedanam.Repository

import com.example.vedanam.MainNetwork
import com.example.vedanam.Model.AttendanceInputModel
import com.example.vedanam.Model.AttendanceModel
import com.example.vedanam.Model.LogInInputModel
import com.example.vedanam.Model.LogInModel

class LogInRepository(val network: MainNetwork) {

    suspend fun getLogIn(request: LogInInputModel):LogInModel{
        val result:LogInModel
        try{
            result= network.fetchLogIn(request)
        }
        catch(cause:Throwable){
            throw  LoginRefreshError("Unable to LogIn",cause)

        }

        return result

    }


}


class LoginRefreshError(message: String, cause: Throwable?) : Throwable(message, cause)