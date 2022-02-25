package com.masphe.accessgithub.extension

import android.app.Activity
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.masphe.accessgithub.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

object Image{
    @JvmStatic
    @BindingAdapter("round_src")
    fun ImageView.setRoundImage(url: String?){
        if (url.isNullOrBlank().not()){
            Glide.with(this).load(url).apply(RequestOptions.bitmapTransform(CircleCrop())).into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("circle_src")
    fun ImageView.setCircleImage(url: String?){
        if (url.isNullOrBlank().not()){
            Glide.with(this).load(url).apply(RequestOptions.bitmapTransform(CircleCrop())).into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("album_src")
    fun ImageView.setStc(url: String?){
        url?.let {
            Glide.with(this.context)
                .load(url)
                .placeholder(R.mipmap.img_loading_error_placeholder)
                .override(100)
                .centerCrop()
                .into(this)
        }
    }
}