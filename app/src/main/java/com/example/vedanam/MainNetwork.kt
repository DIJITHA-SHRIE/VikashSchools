package com.example.vedanam

import androidx.lifecycle.MutableLiveData
import com.example.vedanam.Model.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private val service: MainNetwork by lazy {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://vedanam.co.in/Vikash/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    retrofit.create(MainNetwork::class.java)
}

    fun getNetworkService() = service

interface  MainNetwork{
    @POST("attendance.php")
    suspend fun fetchAttendance(@Body request: AttendanceInputModel):List<AttendanceModel>

    @POST("login.php")
    suspend fun fetchLogIn(@Body request: LogInInputModel):LogInModel

    @POST("subject_list.php")
    suspend fun fetchSubjects(@Body request: SubjectInputModel):List<SubjectOutputModel>

    @POST("manageMarks.php")
    suspend fun fetchManageMarks(@Body request: ManageMarksInputmodel):List<ManageMarksOutputModel>

    @POST("manageMarks.php")
    suspend fun fetchExamList(@Body request: ExamListInputModel):List<ExamListOutputModel>

    @POST("manageMarks.php")
    suspend fun fetchClassList(@Body request: ExamListInputModel):List<ClassListOutputModel>

    @POST("manageMarks.php")
    suspend fun fetchSectionList(@Body request: SectionListInputModel):List<SectionListOutputModel>

    @POST("manageMarks.php")
    suspend fun fetchSubjectList(@Body request: SubjectInputModel):List<SubjectListOutputModel>

    @POST("examSchedule.php")
    suspend fun fetchExamSchedule(@Body request: SectionListInputModel):List<ExamScheduleOutputModel>


    @POST("subject_list.php")
    suspend fun fetchHomeWork(@Body request: ExamListInputModel):List<HomeWorkOutputModel>


    @POST("noticeBoard.php")
    suspend fun fetchNoticeBoard(@Body request: ExamListInputModel):List<NoticeOutputModel>


}