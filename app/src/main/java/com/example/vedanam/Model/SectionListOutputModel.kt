package com.example.vedanam.Model

data class SectionListOutputModel(
    val section_name:String,
    val section_id:String
){
    override fun toString(): String = section_name
}
