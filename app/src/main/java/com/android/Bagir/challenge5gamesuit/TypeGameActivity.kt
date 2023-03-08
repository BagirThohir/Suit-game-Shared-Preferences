package com.android.Bagir.challenge5gamesuit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.Bagir.challenge5gamesuit.databinding.ActivityTypePlayerBinding
import com.google.android.material.snackbar.Snackbar

class TypeGameActivity : AppCompatActivity() {
    private val binding: ActivityTypePlayerBinding by lazy { ActivityTypePlayerBinding.inflate(layoutInflater) }
    val username by lazy { intent.getStringExtra("username") }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initDataView()
        snackBarView()

    }

    private fun initDataView(){
        binding.tvPlayer.text = "$username " + " VS " + getString(R.string.player)
        binding.tvCpu.text = "$username" + " VS " + getString(R.string.cpu)

        binding.imgVsPlayer.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("type",true)
            startActivity(intent)
        }

        binding.imgVsCpu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("type",false)
            startActivity(intent)
        }
    }

    private fun snackBarView(){
        Snackbar.make(binding.root, getString(R.string.welcome) + "$username", Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.close)) {
            }.show()
    }

}