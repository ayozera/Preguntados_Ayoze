package com.example.preguntados_ayoze.activities

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.preguntados_ayoze.model.DataUp
import com.example.preguntados_ayoze.model.Question
import com.example.preguntados_ayoze.R
import com.example.preguntados_ayoze.navigation.Routs
import kotlin.random.Random

@Composable
fun Questions(navController: NavHostController) {
    var index by remember { mutableIntStateOf(0) }
    val questions = DataUp.loader(LocalContext.current)
    val sharedPref = LocalContext.current.getSharedPreferences(
        LocalContext.current.getString(R.string.preference_file_key), Context.MODE_PRIVATE
    )
    var selected by remember { mutableStateOf(false) }
    if (index == -1) {
        index = questions.lastIndex;
    } else if (index > questions.lastIndex) {
        index = 0
    }
    val question = questions[index]
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight()
    ) {
        NavMainMenu(navController)
        QuestionCard(question)
        TrueFalseButtons(question, sharedPref, selected){
                onSelectedChange -> selected = onSelectedChange
        }
        PreviosNextButtons(sharedPref) { indexChange ->
            run {
                index = if (indexChange == 0) Random.nextInt(
                    0,
                    questions.size
                ) else index + indexChange
                selected = false
            }
        }
    }
}

@Composable
fun NavMainMenu(navController: NavHostController){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(0.1f)
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
                navController.navigate(Routs.Statistics.rout)
            }
        ) {
            Text("Statistics")
        }
    }
}

@Composable
fun QuestionCard(question : Question) {
    Text(
        question.question,
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    val imageResourceId = LocalContext.current.resources.getIdentifier(question.img,
        "drawable", LocalContext.current.packageName)
    Image(
        painter = painterResource(id = imageResourceId),
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        contentScale = ContentScale.FillBounds
    )
}
@Composable
fun TrueFalseButtons(question : Question, sharedPref : SharedPreferences, selected : Boolean,
                     onSelectedChange : (Boolean) -> Unit) {
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
                    onSelectedChange(true)
                    if (choice == question.answer){
                        sharedPref.edit()
                            .putInt("Hits", sharedPref.getInt("Hits", 0) + 1)
                            .apply()
                    } else {
                        sharedPref.edit()
                            .putInt("Failures", sharedPref.getInt("Failures", 0) + 1)
                            .apply()
                    }
                    sharedPref.edit()
                        .putInt("TotalClics", sharedPref.getInt("TotalClics", 0) + 1)
                        .apply()
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
                    onSelectedChange(true)
                    if (choice == question.answer){
                        sharedPref.edit()
                            .putInt("Hits", sharedPref.getInt("Hits", 0) + 1)
                            .apply()
                    } else {
                        sharedPref.edit()
                            .putInt("Failures", sharedPref.getInt("Failures", 0) + 1)
                            .apply()
                    }
                    sharedPref.edit()
                        .putInt("TotalClics", sharedPref.getInt("TotalClics", 0) + 1)
                        .apply()
                }
            },
            colors = ButtonDefaults.buttonColors(bottonFalseColor)
        ) {
            Text("False")
        }
    }
}

@Composable
fun PreviosNextButtons(sharedPref : SharedPreferences, onIndexChange: (Int) -> Unit,){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(// Previous button
            onClick = {
                onIndexChange(-1)
                sharedPref.edit()
                    .putInt("TotalClics", sharedPref.getInt("TotalClics", 0) + 1)
                    .apply()
            },
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Icono de una flecha hacia atrás"
            )
            Text("Previous")
        }
        Button( // Random button
            onClick = {
                onIndexChange(0)
                sharedPref.edit()
                    .putInt("TotalClics", sharedPref.getInt("TotalClics", 0) + 1)
                    .apply()
            },
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Text("Random")
        }
        Button( // Next button
            onClick = {
                onIndexChange(1)
                sharedPref.edit()
                    .putInt("TotalClics", sharedPref.getInt("TotalClics", 0) + 1)
                    .apply()
            },
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Text("Next")
            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = "Icono de una flecha hacia adelante"
            )
        }
    }
}
