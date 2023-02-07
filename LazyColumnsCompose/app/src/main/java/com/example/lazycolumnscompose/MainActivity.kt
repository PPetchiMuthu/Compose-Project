package com.example.lazycolumnscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazycolumnscompose.data.dogs
import com.example.lazycolumnscompose.ui.theme.LazyColumnsComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnsComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumnsInfo()
                }
            }
        }
    }
}

@Composable
fun LazyTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Image(
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp),
            painter = painterResource(R.drawable.ic_woof_logo),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.app_name), style = MaterialTheme.typography.displaySmall
        )
    }
}

@Composable
fun LazyColumnsInfo(modifier: Modifier = Modifier) {
//    Scaffold(
//        topBar = { LazyTopBar() }
//    ){
        LazyColumn {
            items(dogs) {
                Card(
                    modifier = modifier.padding(8.dp), elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                    ) {
                        DogImage(it.imageResourceId)
                        DogInformation(it.name, it.age)
                    }
                }
//            }
        }
    }
}

@Composable
fun DogInformation(@StringRes name: Int, age: Int) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 8.dp, start = 10.dp)
    ) {
        Text(text = stringResource(id = name))
        Text(text = "$age years old")
    }
}

@Composable
fun DogImage(@DrawableRes imageResourceId: Int) {
    Image(
        painter = painterResource(id = imageResourceId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(66.dp)
            .padding(8.dp)
            .clip(CircleShape)
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LazyColumnsComposeTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            LazyColumnsInfo()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    LazyColumnsComposeTheme(darkTheme = false) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            LazyColumnsInfo()
        }
    }
}