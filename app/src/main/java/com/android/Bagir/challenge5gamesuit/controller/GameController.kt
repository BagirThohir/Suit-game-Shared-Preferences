package com.android.Bagir.challenge5gamesuit.controller

import com.android.Bagir.challenge5gamesuit.view.GameResult
import com.android.Bagir.challenge5gamesuit.view.GameState

class GameController(
    private val gameResult: GameResult,
    private val playerOne: String?,
    private val playerTwo: String
) : GameState {

    override fun gameState(choiceOne: String, choiceTwo: String) {
        if (choiceOne == choiceTwo) {
            gameResult.resultGame("SERI!")
        } else if (choiceOne == "Batu" && choiceTwo == "Gunting"
            || choiceOne == "Gunting" && choiceTwo == "Kertas"
            || choiceOne == "Kertas" && choiceTwo == "Batu") {
            gameResult.resultGame("$playerOne \n  MENANG!")

        } else {
            gameResult.resultGame("$playerTwo \n MENANG!")
        }
    }
}