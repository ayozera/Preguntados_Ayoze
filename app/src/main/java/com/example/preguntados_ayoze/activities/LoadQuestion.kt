package com.example.preguntados_ayoze.activities

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.preguntados_ayoze.model.DataUp
import com.example.preguntados_ayoze.model.Question


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadQuestion(navController: NavHostController) {
    var answer by remember {
        mutableStateOf(true)
    }
    var bottonTrueColor : Color
    var bottonFalseColor : Color
    if (answer) {
        bottonTrueColor = Color.Green
        bottonFalseColor = Color.Blue
    } else {
        bottonFalseColor = Color.Green
        bottonTrueColor = Color.Blue
    }
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        var textQuestion by remember {
            mutableStateOf("")
        }
        TextField(value = textQuestion,
            onValueChange = {
                    textChange -> textQuestion = textChange
            },
            supportingText = { Text("Introduzca la pregunta") }
        )

        var messageRight by remember {
            mutableStateOf("")
        }
        TextField(value = messageRight,
            onValueChange = {
                    textChange -> messageRight = textChange
            },
            supportingText = { Text("Introduzca un mensaje para felicitar por el acierto") }
        )
        var messageWrong by remember {
            mutableStateOf("")
        }
        TextField(value = messageWrong,
            onValueChange = {
                    textChange -> messageWrong = textChange
            },
            supportingText = { Text("Introduzca un mensaje para regodearse en el fracaso") }
        )

        Text("Selecciona si la respuesta es true o false")
        Row(){
            Button(onClick = { answer = true },
                colors = ButtonDefaults.buttonColors(bottonTrueColor)
            ) {
                Text("True")
            }
            Button(onClick = { answer = false },
                colors = ButtonDefaults.buttonColors(bottonFalseColor)
            ) {
                Text(text = "False")
            }
        }
        val context = LocalContext.current
        Row(){
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Main Menu")
            }
            Button(onClick = {
                if (textQuestion.isBlank() || messageRight.isBlank() || messageWrong.isBlank()) {
                    Toast.makeText(context,"No se admiten campos vacíos", Toast.LENGTH_SHORT).show()
                } else {
                    DataUp.writer(
                        Question(
                            textQuestion,
                            "pentasaedro",
                            answer,
                            messageRight,
                            messageWrong
                        ), context
                    )
                    Toast.makeText(context,"Pregunta cargada correctamente", Toast.LENGTH_SHORT).show()
                    textQuestion = ""
                    messageRight = ""
                    messageWrong = ""
                }
            }) {
                Text(text = "Load Question")
            }
        }
    }
}
