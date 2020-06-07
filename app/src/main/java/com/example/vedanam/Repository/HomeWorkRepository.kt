package com.example.vedanam.Repository

import com.example.vedanam.MainNetwork
import com.example.vedanam.Model.ExamListInputModel
import com.example.vedanam.Model.HomeWorkOutputModel
import com.example.vedanam.Model.SubjectInputModel
import com.example.vedanam.Model.SubjectOutputModel

class HomeWorkRepository (val network: MainNetwork) {

    suspend fun refreshHomeWork(request: ExamListInputModel):List<HomeWorkOutputModel>{
        val result:List<HomeWorkOutputModel>
        try{
            result= network.fetchHomeWork(request)
        }
        catch(cause:Throwable){
            throw  HomeWorkRefreshError("Unable to fetch HomeWork",cause)

        }

        return result

    }


}

class HomeWorkRefreshError(message: String, cause: Throwable?) : Throwable(message, cause)