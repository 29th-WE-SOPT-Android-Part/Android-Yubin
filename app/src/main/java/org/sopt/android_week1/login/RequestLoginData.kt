package org.sopt.android_week1.login

import com.google.gson.annotations.SerializedName

data class RequestLoginData(
    @SerializedName("email")
    val id : String,
    val password : String
)