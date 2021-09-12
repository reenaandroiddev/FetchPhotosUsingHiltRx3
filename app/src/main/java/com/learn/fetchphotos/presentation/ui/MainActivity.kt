package com.learn.fetchphotos.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.learn.fetchphotos.R
import com.learn.fetchphotos.presentation.ui.viewmodel.AlbumPhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val albumPhotosViewModel: AlbumPhotosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        albumPhotosViewModel.albumPhotosList.observe(this, {
            Toast.makeText(this,"Hello:"+ it.size, Toast.LENGTH_LONG).show()
        })

        albumPhotosViewModel.error.observe(this, {
            Toast.makeText(this, it+"Hello", Toast.LENGTH_LONG).show()

        })
        albumPhotosViewModel.getAlbumPhotos(63)

    }
}