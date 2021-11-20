package org.sopt.android_week1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.android_week1.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        clickSignUp()

        setContentView(binding.root)
    }

    private fun clickSignUp() {

        binding.btSignup.setOnClickListener {
            val name = binding.etName.text
            val id = binding.etId.text
            val pw = binding.etPassword.text

            if (name.isEmpty() || id.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent()
                intent.putExtra("id", id.toString())
                intent.putExtra("pw", id.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}