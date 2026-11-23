package com.monday.assistant.data

data class Reminder(

    val id: Int = 0,

    val title: String,

    val description: String = "",

    val triggerTime: Long,

    val isCompleted: Boolean = false,

    val createdAt: Long = System.currentTimeMillis()

)
