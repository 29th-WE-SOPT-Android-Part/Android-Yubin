package org.sopt.android_week1.profile

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ProfileBindingAdapter {
    @JvmStatic
    @BindingAdapter("app:ProfileImg")
    fun setImage (imageview : ImageView, img: String){
        Glide.with(imageview.context)
            .load(img)
            .circleCrop()
            .into(imageview)
    }
}