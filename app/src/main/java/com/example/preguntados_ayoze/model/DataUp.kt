package com.example.preguntados_ayoze.model

import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader

class DataUp {

    companion object {
        fun firstloader(context: Context) {
            val assetManager = context.assets
            val inputStream = assetManager.open("questions.txt")
            val reader = BufferedReader(InputStreamReader(inputStream))
            val writer : FileOutputStream =
                context.openFileOutput("questions.txt", Context.MODE_APPEND)
            reader.forEachLine { line ->
                writer.write("$line\n".toByteArray())
            }
            reader.close()
            writer.close()
        }
        fun loader(context : Context): ArrayList<Question> {
            val questionsList = ArrayList<Question>()
            val file = File(context.filesDir, "questions.txt")
            try {
                if (!file.exists()) {
                    firstloader(context)
                }
                val fileInput = FileInputStream(file)
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

        fun writer(question : Question, context : Context) {
            val content = "\n${question.question}\n${question.img}\n${question.answer}\n" +
                    "${question.messageRight}\n${question.messageWrong}\n"
            val writer : FileOutputStream =
                context.openFileOutput("questions.txt", Context.MODE_APPEND)
            writer.write("$content".toByteArray())
            writer.close()
        }

        fun imagesLoader(context: Context) : ArrayList<String> {
            val assetManager = context.assets
            val inputStream = assetManager.open("images.txt")
            val reader = BufferedReader(InputStreamReader(inputStream))
            var output = ArrayList<String>()
            reader.forEachLine { line ->
               output.add(line)
            }
            reader.close()
            return output
        }
    }
}
