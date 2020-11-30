package com.hca.hcatask.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hca.hcatask.R

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.remove() {
    this.visibility = View.GONE
}
fun Any?.print() {
    Log.v("hca_print", " $this")
}

var options: RequestOptions = RequestOptions()
    .placeholder(R.drawable.ic_baseline_account_box)
    .error(R.drawable.ic_baseline_error_24)

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) = Glide.with(view.context).load(url).apply(options).into(view)

