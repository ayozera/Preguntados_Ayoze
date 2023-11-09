package com.example.preguntados_ayoze.activities

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.preguntados_ayoze.R
import com.example.preguntados_ayoze.model.DataUp
import com.example.preguntados_ayoze.navigation.Routs

@Composable
fun YourTest(navController : NavHostController) {
    var index by remember { mutableStateOf(0) }
    val questions = DataUp.loader(LocalContext.current)
    var hits = 0
    var failure = 0

    if (index == -1) {
        index = questions.lastIndex;
    } else if (index > questions.lastIndex) {
        index = 0
    }

    Column() {
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
                    navController.navigate(Routs.Statistics.rout)
                }
            ) {
                Text("Siguiente")
            }
        }
    }
}