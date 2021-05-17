package com.masphe.accessgithub.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

object Image{
    @JvmStatic
    @BindingAdapter("round_src")
    fun ImageView.setRoundImage(url: String?){
        if (url.isNullOrBlank().not()){
            Glide.with(this).load(url).apply(RequestOptions.bitmapTransform(CircleCrop())).into(this)
        }
    }
}