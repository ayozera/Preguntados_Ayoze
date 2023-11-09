package com.example.preguntados_ayoze.navigation

sealed class Routs(val rout : String) {
    object Main : Routs("main")
    object MainGame : Routs("mainGame")
    object Statistics : Routs("statistics")
    object LoadQuestion : Routs("loadQuestion")
    object YourTest : Routs("yourTest")
}