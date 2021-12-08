package org.sopt.android_week1.signup

import org.sopt.android_week1.login.LoginService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SignUpServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signUpService: LoginService = retrofit.create(LoginService::class.java)
}