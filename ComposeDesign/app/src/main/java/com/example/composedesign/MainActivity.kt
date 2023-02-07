package com.example.composedesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.example.composedesign.ui.theme.ComposeDesignTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDesignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        SearchBar()
                        RowRoundedImageCards()
                        ColumnSquaredImageCards()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(value = "", onValueChange = {}, placeholder = {
        Text("Search")
    }, leadingIcon = {
        Icon(Icons.Default.Search, contentDescription = null)
    }, colors = TextFieldDefaults.textFieldColors(
        containerColor = MaterialTheme.colorScheme.surface
    ), modifier = modifier
        .heightIn(60.dp)
        .fillMaxWidth()
        .padding(horizontal = 12.dp)
    )
}

@Composable
fun RoundedImageCard(
    modifier: Modifier = Modifier, @DrawableRes drawable: Int, @StringRes text: Int
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = text),
            modifier = modifier.paddingFromBaseline(top = 24.dp, bottom = 6.dp)
        )
    }
}

@Composable
fun SquaredImageCard(
    modifier: Modifier = Modifier, @DrawableRes drawable: Int, @StringRes text: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(191.dp)
    ) {
        Image(
            painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.size(66.dp)
        )
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.padding(horizontal = 12.dp)
        )

    }
}

@Composable
fun RowRoundedImageCards() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(12.dp)
    ) {
        items(alignYourBodyData) { item ->
            RoundedImageCard(drawable = item.drawable, text = item.text)
        }
    }
}

@Composable
fun ColumnSquaredImageCards(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        items(favoriteCollectionsData) { item ->
            SquaredImageCard(drawable = item.drawable, text = item.text)
        }
    }
}

@Composable
fun BottomNavigationView(modifier: Modifier) {
    /*BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_home))
            },
            selected = true,
            onClick = {}
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_profile))
            },
            selected = false,
            onClick = {}
        )*/
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int, @StringRes val text: Int
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDesignTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Column {
                SearchBar()
                RowRoundedImageCards()
                ColumnSquaredImageCards()
            }
        }
    }
}