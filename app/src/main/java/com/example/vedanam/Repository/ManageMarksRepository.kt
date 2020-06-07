package com.example.vedanam.Repository

import com.example.vedanam.MainNetwork
import com.example.vedanam.Model.*

class ManageMarksRepository (val network: MainNetwork) {

    suspend fun refreshManageMarks(request: ManageMarksInputmodel):List<ManageMarksOutputModel>{
        val result:List<ManageMarksOutputModel>
        try{
            result= network.fetchManageMarks(request)
        }
        catch(cause:Throwable){
            throw  SubjectRefreshError("Unable to fetch Marks",cause)

        }

        return result

    }
    suspend fun refreshExamList(request: ExamListInputModel):List<ExamListOutputModel>{
        val result:List<ExamListOutputModel>
        try{
            result= network.fetchExamList(request)
        }
        catch(cause:Throwable){
            throw  SubjectRefreshError("Unable to fetch Marks",cause)

        }

        return result

    }
    suspend fun refreshClasslist(request: ExamListInputModel):List<ClassListOutputModel>{
        val result:List<ClassListOutputModel>
        try{
            result= network.fetchClassList(request)
        }
        catch(cause:Throwable){
            throw  SubjectRefreshError("Unable to fetch Marks",cause)

        }

        return result

    }
    suspend fun refreshSectionList(request: SectionListInputModel):List<SectionListOutputModel>{
        val result:List<SectionListOutputModel>
        try{
            result= network.fetchSectionList(request)
        }
        catch(cause:Throwable){
            throw  SubjectRefreshError("Unable to fetch Marks",cause)

        }

        return result

    }
    suspend fun refreshSubjectList(request: SubjectInputModel):List<SubjectListOutputModel>{
        val result:List<SubjectListOutputModel>
        try{
            result= network.fetchSubjectList(request)
        }
        catch(cause:Throwable){
            throw  SubjectRefreshError("Unable to fetch Marks",cause)

        }

        return result

    }



}

class ManageMarksRefreshError(message: String, cause: Throwable?) : Throwable(message, cause) {
}