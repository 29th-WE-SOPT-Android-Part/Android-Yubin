package org.sopt.android_week1.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import org.sopt.android_week1.R
import org.sopt.android_week1.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)

        initTopBar()

        setContentView(binding.root)
    }

    private fun initTopBar() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_onboarding) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.tbLabel.setupWithNavController(navController, appBarConfiguration)
    }
}