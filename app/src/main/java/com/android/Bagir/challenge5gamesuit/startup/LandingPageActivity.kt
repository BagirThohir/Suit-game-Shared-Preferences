package com.android.Bagir.challenge5gamesuit.startup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.Bagir.challenge5gamesuit.databinding.ActivityLandingPageBinding
import com.android.Bagir.challenge5gamesuit.startup.adapter.LandingPageAdapter
import com.android.Bagir.challenge5gamesuit.startup.fragment.LandingPage1Fragment
import com.android.Bagir.challenge5gamesuit.startup.fragment.LandingPage2Fragment
import com.android.Bagir.challenge5gamesuit.startup.fragment.LandingPage3Fragment

class LandingPageActivity : AppCompatActivity() {

    private val binding: ActivityLandingPageBinding by lazy {
        ActivityLandingPageBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val landingPage1 = LandingPage1Fragment()
        val landingPage2 = LandingPage2Fragment()
        val landingPage3 = LandingPage3Fragment()

        val landingPageAdapter = LandingPageAdapter(
            fragmentManager = supportFragmentManager,
            landingPage1, landingPage2, landingPage3)

        binding.viewPager.adapter = landingPageAdapter
        binding.viewIndicator.setViewPager(binding.viewPager)
    }

}