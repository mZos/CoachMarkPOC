package com.zakariya.coachmarkdemo

object MarkData {
    val data =  listOf(
        MarkDetail("btn_1", "It's a button 1"),
        MarkDetail("btn_2", "It's a button 2"),
        MarkDetail("btn_3", "It's a button 3"),
        MarkDetail("btn_4", "It's a button 4")
    )
}

data class MarkDetail(val id:String, val info:String)