package org.sopt.android_week1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.android_week1.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)

        val id = binding.etId.text
        val pw = binding.etPassword.text

        binding.btLogin.setOnClickListener {
            if (id.toString().isEmpty() || pw.toString().isEmpty()) {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "${id}님 환영합니다", Toast.LENGTH_SHORT).show()
                startHomeActivity()
            }
        }
        binding.btSignup.setOnClickListener {
            startSignUpActivity()
        }
        setContentView(binding.root)
    }

    private fun startSignUpActivity() {
        val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun startHomeActivity() {
        val intent = Intent(this@SignInActivity, HomeActivity::class.java)
        startActivity(intent)
    }
}