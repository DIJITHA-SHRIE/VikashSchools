package com.example.vedanam.Repository

import com.example.vedanam.MainNetwork
import com.example.vedanam.Model.ExamListInputModel
import com.example.vedanam.Model.NoticeOutputModel
import com.example.vedanam.Model.SubjectInputModel
import com.example.vedanam.Model.SubjectOutputModel

class NoticeBoardRepository (val network: MainNetwork) {

    suspend fun refreshNotieBoard(request: ExamListInputModel):List<NoticeOutputModel>{
        val result:List<NoticeOutputModel>
        try{
            result= network.fetchNoticeBoard(request)
        }
        catch(cause:Throwable){
            throw  NoticeRefreshError("Unable to fetch Notices",cause)

        }

        return result

    }


}

class NoticeRefreshError(message: String, cause: Throwable?) : Throwable(message, cause)