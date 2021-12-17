package org.sopt.android_week1.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import org.sopt.android_week1.R
import org.sopt.android_week1.databinding.ActivityOnBoardingBinding
import org.sopt.android_week1.ui.main.MainActivity
import org.sopt.android_week1.util.SOPTSharedPreferences
import org.sopt.android_week1.util.Util.shortToast

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)

        initTopBar()
        isAutoLogin()
        setContentView(binding.root)
    }

    private fun isAutoLogin() {
        if(SOPTSharedPreferences.getAutoLogin(this)) {
            shortToast("자동로그인 되었습니다")
            startActivity(Intent(this@OnBoardingActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun initTopBar() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_onboarding) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.tbLabel.setupWithNavController(navController, appBarConfiguration)
    }
}