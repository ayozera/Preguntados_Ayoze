package com.example.preguntados_ayoze

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable


@Composable
fun LoadQuestion() {
    var textQuestion = ""
    TextField(value = textQuestion,
        onValueChange = {
                textChange -> textQuestion = textChange
        })
}