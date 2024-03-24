package com.merabk.moviesapplicationtm.util

import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun ImageView.loadImageWithGlide(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl)
        .circleCrop()
        .into(this)

}

fun <T> Fragment.collectFlow(flow: Flow<T>, onCollect: suspend (T) -> Unit) =
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(onCollect)
        }
    }
