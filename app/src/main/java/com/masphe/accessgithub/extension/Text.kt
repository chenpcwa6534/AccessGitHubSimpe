package com.masphe.accessgithub.extension

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.masphe.accessgithub.R

object Text {
    @JvmStatic
    @BindingAdapter("name")
    fun TextView.setName(name: String?){
        name?.let {
            if (name.isNotEmpty()){
                val sp = name.split(" ")
                this.text = it.substring(0,1)
            }else{
                this.text = "G"
            }
        }
    }

    @JvmStatic
    @BindingAdapter("background_id")
    fun TextView.setBackgroundId(id: Int){
        this.setBackgroundResource(
            when(id % 5){
                0 -> R.drawable.ic_headshot_blue
                1 -> R.drawable.ic_headshot_green
                2 -> R.drawable.ic_headshot_orange
                3 -> R.drawable.ic_headshot_purple
                else -> R.drawable.ic_headshot_red
            }
        )
    }
}