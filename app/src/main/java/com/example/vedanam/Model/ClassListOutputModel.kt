package com.example.vedanam.Model

data class ClassListOutputModel(
    val class_name:String,
    val class_id:String
){
    override fun toString(): String = class_name
}
