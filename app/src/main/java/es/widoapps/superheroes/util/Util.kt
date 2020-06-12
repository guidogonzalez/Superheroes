package es.widoapps.superheroes.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import es.widoapps.superheroes.R

fun getProgressDrawable(context: Context): CircularProgressDrawable {

    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.cargarImagen(uri: String?, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions
        .placeholderOf(progressDrawable)
        .error(R.mipmap.ic_launcher)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}

@BindingAdapter("android:urlImagen")
fun cargarImagen(view: ImageView, url: String?) {
    view.cargarImagen(url, getProgressDrawable(view.context))
}