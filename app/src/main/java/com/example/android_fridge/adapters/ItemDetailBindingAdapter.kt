package com.example.android_fridge.adapters

import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.android_fridge.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: FloatingActionButton, isGone: Boolean?) {
    if (isGone == null || isGone) {
        view.hide()
    } else {
        view.show()
    }
}

@BindingAdapter("inFridge")
fun bindInFridge(view: FloatingActionButton, inFridge: Boolean?) {
    if (inFridge == null || inFridge) {
        view.setImageResource(R.drawable.ic_remove)
    } else {
        view.setImageResource(R.drawable.ic_plus)
    }
}

@BindingAdapter("renderHtml")
fun bindRenderHtml(view: TextView, description: String?) {
    if (description != null) {
        view.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        view.movementMethod = LinkMovementMethod.getInstance()
    } else {
        view.text = ""
    }
}

@BindingAdapter("restockText")
fun bindWateringText(textView: TextView, wateringInterval: Int) {
    val resources = textView.context.resources
    val quantityString = "once you run out"

    textView.text = quantityString
}
