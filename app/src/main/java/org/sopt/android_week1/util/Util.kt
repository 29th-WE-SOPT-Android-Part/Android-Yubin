package org.sopt.android_week1.util

import android.content.Context
import android.widget.Toast

object Util {
    fun Context.shortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}