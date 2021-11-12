package org.sopt.android_week1.login

import org.sopt.android_week1.signup.RequestSignUpData
import org.sopt.android_week1.signup.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers("Content-Type:application/json")
    @POST("user/login")
    fun postLogin(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>

    @Headers("Content-Type:application/json")
    @POST("user/signup")
    fun postSignUp(
        @Body body : RequestSignUpData
    ) : Call<ResponseSignUpData>
}