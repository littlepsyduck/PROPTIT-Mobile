package com.proptit.androidoverview

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val imageView = findViewById<ImageView>(android.R.id.background)
//        Glide.with(this)
//            .load(R.drawable.background)
//            .apply(RequestOptions.bitmapTransform(RoundedCorners(40)))
//            .into(imageView)
    }
}