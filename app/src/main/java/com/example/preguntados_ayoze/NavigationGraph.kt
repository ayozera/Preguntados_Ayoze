package com.example.preguntados_ayoze

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routs.Main.rout){
        composable(Routs.Main.rout){
            Main(navController)
        }
        composable(Routs.Preguntados.rout){
            Questions(navController)
        }
        composable(Routs.Statistics.rout){
            Statistics(navController)
        }
    }
}

