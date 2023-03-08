package com.android.Bagir.challenge5gamesuit

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.android.Bagir.challenge5gamesuit.controller.GameController
import com.android.Bagir.challenge5gamesuit.databinding.ActivityMainBinding
import com.android.Bagir.challenge5gamesuit.databinding.DialogResultBinding
import com.android.Bagir.challenge5gamesuit.view.GameResult

class MainActivity : AppCompatActivity(), GameResult, View.OnClickListener {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val username by lazy { intent.getStringExtra("username") }
    val type by lazy { intent.getBooleanExtra("type", true) }
    private var playerOneResults = ""
    private var playerTwoResults = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvPlayaer1.text = username
        if (type) {
            initDataPlayer()
        } else {
            initDataCom()
        }

        initViewConfig()
    }

    private fun initDataPlayer() {
        binding.tvOtherPlayer.text = getString(R.string.player_2)

        val playerChoiceOne = arrayListOf(
            binding.imgRockPlayer1, binding.imgPaperPlayer1, binding.imgScissorsPlayer1
        )
        val playerChoiceTwo = arrayListOf(
            binding.imgRockPlayer2, binding.imgPaperPlayer2, binding.imgScissorsPlayer2
        )

        disableChoice2(false)
        val controller = GameController(this, username, getString(R.string.player_2))
        playerChoiceOne.forEachIndexed { index, ImageView ->
            ImageView.setOnClickListener {
                playerOneResults = playerChoiceOne[index].contentDescription.toString()

                disableChoice(false)
                disableChoice2(true)

                playerChoiceOne.forEach {
                    it.setBackgroundResource(android.R.color.transparent)
                }
                playerChoiceOne[index].setBackgroundResource(R.drawable.background_selected)
            }
        }

        playerChoiceTwo.forEachIndexed { index, ImageView ->
            ImageView.setOnClickListener {
                playerTwoResults = playerChoiceTwo[index].contentDescription.toString()
                disableChoice2(false)
                if (playerOneResults != "") {
                    controller.gameState(playerTwoResults, playerOneResults)
                    playerChoiceTwo.forEach {
                        it.setBackgroundResource(android.R.color.transparent)
                    }
                    playerChoiceTwo[index].setBackgroundResource(R.drawable.background_selected)
                }
            }
        }
    }

    private fun initDataCom() {
        binding.tvOtherPlayer.text = getString(R.string.cpu)

        val playerChoiceOne = arrayListOf(
            binding.imgRockPlayer1, binding.imgPaperPlayer1, binding.imgScissorsPlayer1
        )
        val playerChoiceCom = arrayListOf(
            binding.imgRockPlayer2, binding.imgPaperPlayer2, binding.imgScissorsPlayer2
        )

        val controller = GameController(this, username, getString(R.string.cpu))
        playerChoiceOne.forEachIndexed { index, ImageView ->
            ImageView.setOnClickListener {
                val hasilCom = playerChoiceCom.random()
                val hasilPemain = playerChoiceOne[index].contentDescription.toString()

                disableChoice(false)

                hasilCom.setBackgroundResource(R.drawable.background_selected)
                controller.gameState(
                    hasilPemain,
                    hasilCom.contentDescription.toString()
                )

                playerChoiceOne.forEach {
                    it.setBackgroundResource(android.R.color.transparent)
                }
                playerChoiceOne[index].setBackgroundResource(R.drawable.background_selected)
            }
        }
    }

    private fun initViewConfig() {
        binding.imgClose.setOnClickListener(this)
        binding.imgRefresh.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        binding.apply {
            when (view) {
                imgClose -> {
                    finish()
                }
                imgRefresh -> {
                    newGame()
                }
            }
        }
    }

    private fun disableChoice(isEnable: Boolean) {
        binding.imgRockPlayer1.isEnabled = isEnable
        binding.imgScissorsPlayer1.isEnabled = isEnable
        binding.imgPaperPlayer1.isEnabled = isEnable
    }

    private fun disableChoice2(isEnable: Boolean) {
        binding.imgRockPlayer2.isEnabled = isEnable
        binding.imgScissorsPlayer2.isEnabled = isEnable
        binding.imgPaperPlayer2.isEnabled = isEnable
    }

    override fun resultGame(result: String) {
        val dialog = Dialog(this)
        val dialogResultBinding = DialogResultBinding.inflate(LayoutInflater.from(this))
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogResultBinding.root)
        dialog.setCancelable(false)
        dialogResultBinding.tvResult.text = result
        dialogResultBinding.btnMainLagi.setOnClickListener {
            newGame()
            dialog.dismiss()
        }
        dialogResultBinding.btnMenu.setOnClickListener {
            finish()
        }
        dialog.show()
    }

    private fun newGame() {
        binding.imgRockPlayer1.setBackgroundResource(android.R.color.transparent)
        binding.imgScissorsPlayer1.setBackgroundResource(android.R.color.transparent)
        binding.imgPaperPlayer1.setBackgroundResource(android.R.color.transparent)
        binding.imgRockPlayer2.setBackgroundResource(android.R.color.transparent)
        binding.imgScissorsPlayer2.setBackgroundResource(android.R.color.transparent)
        binding.imgPaperPlayer2.setBackgroundResource(android.R.color.transparent)
        playerOneResults = ""
        playerTwoResults = ""
        disableChoice(true)
        disableChoice2(false)
    }


}