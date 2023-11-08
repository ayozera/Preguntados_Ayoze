package com.example.preguntados_ayoze

sealed class Routs(val rout : String) {
    object Main : Routs("main")
    object Preguntados : Routs("preguntados")
    object Statistics : Routs("statistics")
}