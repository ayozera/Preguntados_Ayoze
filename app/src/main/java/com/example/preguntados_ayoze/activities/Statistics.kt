package com.example.preguntados_ayoze.activities

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.preguntados_ayoze.R

@Composable
fun Statistics(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        val sharedPref = LocalContext.current.getSharedPreferences(
            LocalContext.current.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        var hits = sharedPref.getInt("Hits", 0)
        var failures = sharedPref.getInt("Failures", 0)
        var totalClics = sharedPref.getInt("TotalClics", 0)
        var reset by remember {
            mutableStateOf(false)
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text(text = "Go Back")
            }
            Button(
                onClick = {
                    sharedPref.edit()
                        .putInt("Hits", 0)
                        .putInt("Failures", 0)
                        .putInt("TotalClics", 0)
                        .apply()
                    navController.popBackStack()
                }
            ) {
                Text(text = "Reset Stadistics")
            }
        }

        Text("Successes:   $hits")
        Text("Failures:    $failures")
        Text("Total Clics: $totalClics")
    }
}