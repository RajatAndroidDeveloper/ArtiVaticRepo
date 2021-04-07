package com.artivaticinterviewtest.utils

import android.widget.ImageView
import com.artivaticinterviewtest.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.net.URL

object Utils {
    fun ImageView.setImageUrl(url: String?) {
        val requestOptions = RequestOptions()
            .placeholder(R.color.black)
        this.context?.applicationContext?.let {
            Glide.with(it).load(URL(url))
                .apply(requestOptions)
                .into(this)
        }
    }
}