package com.merabk.moviesapplicationtm.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadImageWithGlide(url: String, placeholderResId: Int) {
    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(placeholderResId)
        .into(this)
}