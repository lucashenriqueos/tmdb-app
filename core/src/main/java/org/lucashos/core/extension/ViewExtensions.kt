package org.lucashos.core.extension

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

infix fun ImageView.loadImage(path: String) = Glide.with(this).load(path).into(this)