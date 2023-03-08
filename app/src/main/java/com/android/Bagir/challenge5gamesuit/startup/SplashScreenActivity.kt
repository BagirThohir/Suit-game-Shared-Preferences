package com.android.Bagir.challenge5gamesuit.startup

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContentProviderCompat.requireContext
import com.android.Bagir.challenge5gamesuit.TypeGameActivity
import com.android.Bagir.challenge5gamesuit.databinding.ActivitySplashScreenBinding
import com.android.Bagir.challenge5gamesuit.loadImage
import com.android.Bagir.challenge5gamesuit.startup.fragment.LandingPage3Fragment
import com.android.Bagir.challenge5gamesuit.startup.fragment.LandingPage3Fragment.Companion.NAME_ID

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val binding: ActivitySplashScreenBinding by lazy {
        ActivitySplashScreenBinding.inflate(
            layoutInflater
        )
    }
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        SetUpshredPreference()


        val url = "https://i.ibb.co/HC5ZPgD/splash-screen1.png"
        binding.imgLogo1.loadImage(url)
        val name = sharedPreferences.getString(NAME_ID,"-")


        val delaySplash = 3000L
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = if (name != "-") {
                Intent(this, TypeGameActivity::class.java)

            }else{
                Intent(this, LandingPageActivity::class.java)
            }
            intent.putExtra("username",name)

            startActivity(intent)
            finish()
        }, delaySplash)

    }

    private fun SetUpshredPreference() {

        sharedPreferences =
            this.getSharedPreferences(LandingPage3Fragment.TABLE_DATA, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

}