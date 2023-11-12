package com.example.preguntados_ayoze.activities

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.preguntados_ayoze.model.DataUp
import com.example.preguntados_ayoze.model.Question
import com.example.preguntados_ayoze.navigation.Routs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun YourTest(navController : NavHostController) {
    var index by remember { mutableIntStateOf(0) }
    val questions = DataUp.loader(LocalContext.current)
    var hits by remember { mutableIntStateOf(0) }
    var last by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val question = questions[index]
    var selected by remember { mutableStateOf(false) }
    val delay = rememberCoroutineScope()

    Column() {
        NavMenuTest(navController, last){ onNavChange ->
            run{
                selected = false
                if(onNavChange) {
                    var finalMessage : String
                    var divisor = questions.size.toDouble()
                    if (divisor == 0.0) {
                        divisor = 1.0
                    }
                    val mark = 10 * hits / divisor
                    finalMessage = String.format("%.2f | ", mark)
                    finalMessage +=
                        if (mark >= 9) "Eres una máquina. No se te escapa ni una."
                        else if (mark >= 6) "Te falta mejorar un poco, pero vas en camino"
                        else "Me cuesta pensar en un mensaje lo suficiente himillante para ti"

                    Toast.makeText(context, finalMessage, Toast.LENGTH_LONG).show()
                    delay.launch(Dispatchers.Main) {
                        delay(3000)
                        navController.popBackStack()
                    }
                } else  {
                    index++
                    if(index == questions.lastIndex){
                        last = true
                    }
                }
            }
        }
        QuestionCard(question)
        OptionButtons(question, selected){ onHitAnswer ->
            if(onHitAnswer) {
                hits++
            }
            selected = true
        }
    }
}

@Composable
fun NavMenuTest(navController: NavHostController, last: Boolean, onLastChange : (Boolean) -> Unit) {
    val text = if(last) "End Test" else "Next"
    Row(
        modifier = Modifier
            .fillMaxHeight(0.1f)
            .fillMaxWidth()
    ) {
        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Main menu")
        }
        Button(
            onClick = {
                onLastChange(last)
            }
        ) {
            Text(text)
        }
    }
}

@Composable
fun OptionButtons(question : Question, selected : Boolean, onHitAnswer : (Boolean) -> Unit) {
    var choice by remember { mutableStateOf(false) }
    var bottonTrueColor = Color.Blue
    var bottonFalseColor = Color.Blue

    if (selected) {
        val message: String
        if (choice == question.answer) {
            message = question.messageRight
            if (choice) {
                bottonTrueColor = Color.Green
            } else {
                bottonFalseColor = Color.Green

            }
        } else {
            message = question.messageWrong
            if (choice) {
                bottonTrueColor = Color.Red

            } else {
                bottonFalseColor = Color.Red
            }
        }
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()
    } else {
        bottonTrueColor = Color.Blue
        bottonFalseColor = Color.Blue
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(// True button
            onClick = {
                if (!selected) {
                    choice = true
                    onHitAnswer(choice == question.answer)
                }
            },
            colors = ButtonDefaults.buttonColors(bottonTrueColor)
        ) {
            Text("True")
        }
        Button(// False button
            onClick = {
                if (!selected) {
                    choice = false
                    onHitAnswer(choice == question.answer)
                }
            },
            colors = ButtonDefaults.buttonColors(bottonFalseColor)
        ) {
            Text("False")
        }
    }
}