package org.sopt.android_week1.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.android_week1.data.remote.RequestLoginData
import org.sopt.android_week1.data.remote.ResponseLoginData
import org.sopt.android_week1.data.remote.ResponseWrapper
import org.sopt.android_week1.data.remote.ServiceCreator
import org.sopt.android_week1.databinding.ActivitySignInBinding
import org.sopt.android_week1.ui.main.MainActivity
import org.sopt.android_week1.util.SOPTSharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    private val signUpActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val id = it.data?.getStringExtra("id")
                val pw = it.data?.getStringExtra("pw")
                binding.etId.setText(id)
                binding.etPassword.setText(pw)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)

        clickBtnCheck()
        clickLoginBtn()
        clickSignUpBtn()

        setContentView(binding.root)
    }

    private fun clickBtnCheck() {
        binding.ibCheck.setOnClickListener {
            binding.ibCheck.isSelected = !binding.ibCheck.isSelected
            SOPTSharedPreferences.setAutoLogin(this, binding.ibCheck.isSelected)
        }
    }

    private fun clickLoginBtn() {
        binding.btLogin.setOnClickListener {
            initNetwork()
        }
    }

    private fun clickSignUpBtn() {
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
    }

    private fun initNetwork() {
        val requestLoginData = RequestLoginData(
            id = binding.etId.text.toString(),
            password = binding.etPassword.text.toString()
        )

        val call: Call<ResponseWrapper<ResponseLoginData>> = ServiceCreator.loginService.postLogin(requestLoginData)

        call.enqueue(object: Callback<ResponseWrapper<ResponseLoginData>> {
            override fun onResponse(
                call: Call<ResponseWrapper<ResponseLoginData>>,
                response: Response<ResponseWrapper<ResponseLoginData>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data

                    Toast.makeText(this@SignInActivity, "${data?.name}님 반갑습니다!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                } else
                    Toast.makeText(this@SignInActivity, "로그인에 실패하였습니다", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseWrapper<ResponseLoginData>>, t: Throwable) {
                Log.e("NetworkText", "error:$t")
            }
        })
    }
}