package com.example.vedanam.Model

data class ExamScheduleOutputModel(
    val exam_name:String,
    val subject_name:String,
    val date:String,
    val start_time:String,
    val start_min:String,
    val starting_ampm:String,
    val end_time:String,
    val end_min:String,
    val ending_ampm:String
)