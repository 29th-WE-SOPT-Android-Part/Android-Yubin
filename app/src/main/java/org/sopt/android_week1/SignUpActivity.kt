package org.sopt.android_week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.android_week1.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        val name = binding.etName.text
        val id = binding.etId.text
        val pw = binding.etPassword.text

        binding.btSignup.setOnClickListener {
            if (name.toString().isEmpty() || id.toString().isEmpty() || pw.toString().isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
            else
                finish()
        }
        setContentView(binding.root)
    }
}