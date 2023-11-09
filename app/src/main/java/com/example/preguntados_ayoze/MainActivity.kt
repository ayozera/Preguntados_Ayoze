package com.example.preguntados_ayoze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.preguntados_ayoze.navigation.NavigationGraph
import com.example.preguntados_ayoze.navigation.Routs
import com.example.preguntados_ayoze.ui.theme.Preguntados_AyozeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Preguntados_AyozeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph()
                }
            }
        }
    }
}

@Composable
fun Main(navController: NavHostController) {
    Column(){
        Button(
            onClick = {
                navController.navigate(Routs.MainGame.rout)
            }
        ) {
            Text(text = "PreguntaDos")
        }
        Button(
            onClick = { 
                navController.navigate(Routs.YourTest.rout)
            }
        ) {
            Text(text = "Test Yourself")
        }
        Button(
            onClick = {
                navController.navigate(Routs.LoadQuestion.rout)
            }
        ) {
            Text(text = "Add Question")
        }
    }
}


