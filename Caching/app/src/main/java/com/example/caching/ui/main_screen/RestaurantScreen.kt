package com.example.caching.ui.main_screen

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.Glide
import com.example.caching.data.local.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun MainScreen(
    viewModel: RestaurantViewModel = hiltViewModel()
) {
    val restaurantListState by viewModel.restaurantList.collectAsState()
    val restaurantList: List<Restaurant> = restaurantListState.data ?: emptyList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn() {
            items(restaurantList) { restaurant ->
                RestaurantItem(restaurant = restaurant)
            }
        }
    }
}

@Composable
fun RestaurantItem(
    restaurant: Restaurant, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 10.dp)
    ) {
        val context = LocalContext.current
        val imageState = remember { mutableStateOf<ImageBitmap?>(null) }
        LaunchedEffect(restaurant.logo) {
            val drawable: Drawable? = withContext(Dispatchers.IO) {
                Glide.with(context).load(restaurant.logo).submit().get()
            }
            imageState.value = drawable?.toBitmap()?.asImageBitmap()
        }
        imageState.value?.let { imageBitmap ->
            Image(
                bitmap = imageBitmap,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
            )
        } ?: kotlin.run {
            Box(modifier = Modifier.size(120.dp)) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = restaurant.name,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = restaurant.type,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = restaurant.address,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}