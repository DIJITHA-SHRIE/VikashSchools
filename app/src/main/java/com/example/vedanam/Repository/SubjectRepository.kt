package com.example.vedanam.Repository

import com.example.vedanam.MainNetwork
import com.example.vedanam.Model.SubjectInputModel
import com.example.vedanam.Model.SubjectOutputModel

class SubjectRepository (val network: MainNetwork) {

    suspend fun refreshSubject(request: SubjectInputModel):List<SubjectOutputModel>{
        val result:List<SubjectOutputModel>
        try{
            result= network.fetchSubjects(request)
        }
        catch(cause:Throwable){
            throw  SubjectRefreshError("Unable to fetch subjects",cause)

        }

        return result

    }


}

class SubjectRefreshError(message: String, cause: Throwable?) : Throwable(message, cause)