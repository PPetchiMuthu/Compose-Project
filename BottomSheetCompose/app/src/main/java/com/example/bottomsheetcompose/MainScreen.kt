package com.example.bottomsheetcompose

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(context: Context,viewModel:MainViewModel) {
    var bottomSheetVisible by remember { mutableStateOf(false) }
    val skipHalfExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberSheetState(skipHalfExpanded = skipHalfExpanded)
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            bottomSheetVisible = true
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Image")
        }
    },
        content = {
            val imageBitmap: List<Bitmap> = viewModel.imageBitmap
            ListOfImage(imageBitmaps = imageBitmap)
        }
    )
    if (bottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { bottomSheetVisible = false },
            sheetState = bottomSheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(
                        // Note: If you provide logic outside of onDismissRequest to remove the sheet,
                        // you must additionally handle intended state cleanup, if any.
                        onClick = {
                            scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                                if (!bottomSheetState.isVisible) {
                                    viewModel.dismiss()
                                    bottomSheetVisible = false
                                }
                            }
                        }) {
                        Text("Hide")
                    }
                    Button(
                        // Note: If you provide logic outside of onDismissRequest to remove the sheet,
                        // you must additionally handle intended state cleanup, if any.
                        onClick = {
                            scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                                if (!bottomSheetState.isVisible) {
                                    viewModel.addAllImageUris()
                                    bottomSheetVisible = false
                                }
                            }
                        }) {
                        Text("Done")
                    }
                }
                val imageUris: List<Uri> = viewModel.getImageUris(context)
                BottomSheetImage(imageUris = imageUris,
                    onSelectImage = {
                        viewModel.addImageUri(it)
                    })
            }
        }
    }
}

@Composable
fun ListOfImage(
    modifier: Modifier = Modifier, imageBitmaps: List<Bitmap>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        items(imageBitmaps) { bitmap: Bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .size(118.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun BottomSheetImage(
    modifier: Modifier = Modifier,
    onSelectImage: (Bitmap) -> Unit,
    imageUris: List<Uri>
) {
    val context = LocalContext.current
    val contentResolver = context.contentResolver
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        items(imageUris) { uri: Uri ->
            var bitmap by remember(uri) {
                mutableStateOf<Bitmap?>(null)
            }
            var isSelected by remember {
                mutableStateOf(false)
            }
            LaunchedEffect(uri) {
                withContext(Dispatchers.IO) {
                    bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        val source = ImageDecoder.createSource(contentResolver, uri)
                        ImageDecoder.decodeBitmap(source)
                    } else {
                        MediaStore.Images.Media.getBitmap(contentResolver, uri)
                    }
                }
            }

            bitmap?.let { image ->
                ImageItem(image, isSelected = isSelected, onSelectImage = {
                    onSelectImage(image)
                    isSelected = !isSelected
                })
            } ?: run {
                Box(modifier = Modifier.size(150.dp)) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}


@Composable
fun ImageItem(bitmap: Bitmap, isSelected: Boolean, onSelectImage: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onSelectImage() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .size(118.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            alpha = if (isSelected) 0.5f else 1f
        )
    }
}
