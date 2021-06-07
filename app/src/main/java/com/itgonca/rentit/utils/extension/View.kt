package com.itgonca.rentit.utils.extension

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImageUrl(path: String) {
    Glide.with(this)
        .load(path)
        .into(this)
}