package com.example.preguntados_ayoze.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.preguntados_ayoze.activities.Main
import com.example.preguntados_ayoze.activities.LoadQuestion
import com.example.preguntados_ayoze.activities.Questions
import com.example.preguntados_ayoze.activities.Statistics
import com.example.preguntados_ayoze.activities.YourTest

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routs.Main.rout){
        composable(Routs.Main.rout){
            Main(navController)
        }
        composable(Routs.MainGame.rout){
            Questions(navController)
        }
        composable(Routs.Statistics.rout){
            Statistics(navController)
        }
        composable(Routs.LoadQuestion.rout){
            LoadQuestion(navController)
        }
        composable(Routs.YourTest.rout){
            YourTest(navController)
        }
    }
}

