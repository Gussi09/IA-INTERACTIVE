package com.gacd.iainteractive.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

//Use Third Library to Load Image in ImageView control

@BindingAdapter("image")

fun loadImage(view: ImageView, url: String){
    Glide.with(view)
        .load(url)
        .into(view)
}