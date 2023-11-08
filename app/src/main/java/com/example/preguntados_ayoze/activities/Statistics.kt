package com.example.preguntados_ayoze.activities

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.preguntados_ayoze.R

@Composable
fun Statistics(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = { 
                navController.popBackStack()
            }
        ) {
            Text(text = "Go Back")
        }
        val sharedPref = LocalContext.current.getSharedPreferences(
            LocalContext.current.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        var hits = sharedPref.getInt("Hits", 0)
        var failures = sharedPref.getInt("Failures", 0)
        var totalClics = sharedPref.getInt("TotalClics", 0)
        Text("Successes:   $hits")
        Text("Failures:    $failures")
        Text("Total Clics: $totalClics")
    }
}