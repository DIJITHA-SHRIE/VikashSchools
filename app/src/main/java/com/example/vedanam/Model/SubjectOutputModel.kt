package com.example.vedanam.Model

data class SubjectOutputModel(
    val class_name:String,
    val subject_name:String,
    val teacher_name:String
)
data class SubjectListOutputModel(
    val subject_name:String,
    val subject_id:String
){
    override fun toString(): String = subject_name
}