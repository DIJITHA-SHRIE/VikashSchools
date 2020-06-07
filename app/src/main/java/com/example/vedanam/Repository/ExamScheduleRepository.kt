package com.example.vedanam.Repository

import com.example.vedanam.MainNetwork
import com.example.vedanam.Model.ExamScheduleOutputModel
import com.example.vedanam.Model.SectionListInputModel
import com.example.vedanam.Model.SubjectInputModel
import com.example.vedanam.Model.SubjectOutputModel

class ExamScheduleRepository (val network: MainNetwork) {

    suspend fun refreshExamSchedule(request: SectionListInputModel):List<ExamScheduleOutputModel>{
        val result:List<ExamScheduleOutputModel>
        try{
            result= network.fetchExamSchedule(request)
        }
        catch(cause:Throwable){
            throw  ExamScheduleRefreshError("Unable to fetch the exam Schedule",cause)

        }

        return result

    }


}

class ExamScheduleRefreshError(message: String, cause: Throwable?) : Throwable(message, cause)