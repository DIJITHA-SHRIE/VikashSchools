package com.example.vedanam.Model

data class ExamListOutputModel (
    val exam_name:String,
    val exam_id:String
){
    override fun toString(): String = exam_name
}
