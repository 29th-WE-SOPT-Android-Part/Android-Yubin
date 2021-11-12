package org.sopt.android_week1.login

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val retrofit : Retrofit = Retrofit.Builder()    // retrofit 객체 생성
        .baseUrl(BASE_URL)  // 빌더 객체의 baseUrl 호출. 서버의 메인 URL 전달
        .addConverterFactory(GsonConverterFactory.create()) // gson 컨버터 연동
        .build()    // Retrofit 객체 반환

    val loginService : LoginService = retrofit.create(LoginService::class.java)
}