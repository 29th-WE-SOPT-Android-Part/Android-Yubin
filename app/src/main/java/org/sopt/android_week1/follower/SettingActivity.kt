package org.sopt.android_week1.follower

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.android_week1.R
import org.sopt.android_week1.databinding.ActivitySettingBinding
import org.sopt.android_week1.main.MainActivity
import org.sopt.android_week1.util.SOPTSharedPreferences

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        initSwitch()
        clickSwitch()
        clickBtnBack()
        setContentView(binding.root)
    }

    private fun initSwitch() {
        binding.switchAutoLogin.isChecked = SOPTSharedPreferences.getAutoLogin(this)
    }

    private fun clickSwitch() {
        binding.switchAutoLogin.setOnClickListener {
            SOPTSharedPreferences.setAutoLogin(this, binding.switchAutoLogin.isSelected)
        }
    }

    private fun clickBtnBack() {
        binding.ivBack.setOnClickListener {
            startActivity(Intent(this@SettingActivity, MainActivity::class.java))
            finish()
        }
    }
}