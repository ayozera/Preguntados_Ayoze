package com.example.preguntados_ayoze

data class Question(
    val question: String,
    val img: String,
    val answer: Boolean,
    val messageRight: String,
    val messageWrong: String
)