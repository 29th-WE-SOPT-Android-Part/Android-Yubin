package org.sopt.android_week1.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.sopt.android_week1.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            initNetwork()
        }
    }

    private fun initNetwork() {
        val requestSignUpData = RequestSignUpData(
            id = binding.etId.text.toString(),
            name = binding.etName.text.toString(),
            password = binding.etPassword.text.toString()
        )

        val call: Call<ResponseSignUpData> =
            SignUpServiceCreator.signUpService.postSignUp(requestSignUpData)

        call.enqueue(object : Callback<ResponseSignUpData> {
            override fun onResponse(
                call: Call<ResponseSignUpData>,
                response: Response<ResponseSignUpData>
            ) {
                if (response.isSuccessful) {
                    val intent = Intent()
                    intent.putExtra("id", requestSignUpData.id)
                    intent.putExtra("pw", requestSignUpData.password)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(this@SignUpActivity, "회원 가입에 실패하였습니다", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }


}