package com.example.preguntados_ayoze

import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader

class DataUp {

    companion object {
        fun loader(context : Context): ArrayList<Question> {
            /*
            val assetManager = context.assets
            val inputStream = assetManager.open("questions.txt")
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
             */

            val file = File(context.filesDir, "questions.txt")
            val fileInput = FileInputStream(file)
            val questionsList = ArrayList<Question>()
            try {
                val reader = BufferedReader(InputStreamReader(fileInput))
                var counter = -1
                var question = ""
                var img = ""
                var answer = false
                var messageRigth = ""
                var messageWrong = ""
                reader.forEachLine { line ->
                    if (line.isNotBlank()) {
                        counter++
                        when (counter) {
                            0 -> question = line
                            1 -> img = line
                            2 -> answer = line.contains("true")
                            3 -> messageRigth = line
                            4 -> {
                                messageWrong = line
                                counter = -1
                                questionsList.add(Question(question, img, answer, messageRigth,messageWrong))
                            }
                        }
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return questionsList
        }
    }
}
