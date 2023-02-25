package com.example.bottomsheetcompose

import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var _imageBitmap = mutableStateListOf<Bitmap>()
    val imageBitmap: List<Bitmap> = _imageBitmap

    private var bitmaps = mutableStateListOf<Bitmap>()

    fun addImageUri(bitMap: Bitmap) {
        if (!bitmaps.contains(bitMap)) {
            bitmaps.add(bitMap)
        } else {
            bitmaps.remove(bitMap)
        }
    }

    fun dismiss() {
        bitmaps = mutableStateListOf()
    }

    fun addAllImageUris() {
        bitmaps.forEach {
            if(!_imageBitmap.contains(it)) {
                _imageBitmap.add(it)
            }
        }
        bitmaps = mutableStateListOf()
    }

    fun getImageUris(context: Context): List<Uri> {
        val allImageUris = mutableListOf<Uri>()
        viewModelScope.launch {
            val cursor = context.contentResolver?.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Images.Media.DATE_ADDED
            )
            cursor?.use {
                val columnIndex = it.getColumnIndex(MediaStore.Images.Media._ID)
                while (it.moveToNext()) {
                    val imageId = it.getLong(columnIndex)
                    val imageUri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        imageId
                    )
                    allImageUris.add(imageUri)
                }
            }
        }
        return allImageUris
    }
}