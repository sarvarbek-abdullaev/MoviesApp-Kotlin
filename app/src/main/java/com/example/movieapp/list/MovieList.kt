package com.example.movieapp.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.movieapp.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.movieapp.models.Movie
import com.example.movieapp.utils.Constants

@Composable
fun MoviesList(
    genre: String,
    onMovieClick: (String) -> Unit = {},
    viewModel: ListViewModel = ListViewModel(genre = genre)
) {
    val isProgressVisible = remember { mutableStateOf(true) }
    CustomCircularProgressBar(isProgressVisible.value)

    Column(
        modifier = Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        val movies by viewModel.moviesLiveData.observeAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(R.color.movies_bg)
                )
                .padding(0.dp, 0.dp, 0.dp, 50.dp),
        ) {
            movies?.let {
                items(items = it.toList(), itemContent = { item ->
                    MovieList(movie = item, onMovieClick = onMovieClick)
                })
            }
        }
        movies?.let {
            isProgressVisible.value = movies!!.isEmpty()
        }
    }
}

@Composable
fun MovieList(movie: Movie, onMovieClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 10.dp, 15.dp, 10.dp)
            .clickable {
                onMovieClick(movie.id.toString())
            },
            Arrangement.Center,
            Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Constants.IMGURL + movie.poster_path)
                .crossfade(true)
                .size(Size.ORIGINAL)
                .build(),
            contentDescription = movie.title,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(
                    colorResource(R.color.movie_bg)
                ),
        ) {
            Row(modifier = Modifier
                .padding(16.dp),
                Arrangement.SpaceBetween,
                Alignment.CenterVertically
            ) {
                Name(name = movie.title)
                Rating(rating = movie.vote_average)
            }
        }
    }
}

@Composable
fun Name(name: String) {
    Text(
        modifier = Modifier.fillMaxWidth(0.7f),
        text = name,
        color = Color.Black,
        fontSize = 20.sp,
        textAlign = TextAlign.Left
    )
}

@Composable
fun Rating(rating: Float) {
    var color = colorResource(R.color.score_red)

    if (rating >= 8) color = colorResource(R.color.score_green)
    else if (rating >= 5) color = colorResource(R.color.score_orange)

    Text(
        modifier = Modifier.fillMaxWidth(),
        text = rating.toString(),
        color = color,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Right,
    )
}

@Composable
private fun CustomCircularProgressBar(isVisible: Boolean) {
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxSize(1f)
                    .align(Alignment.Center),
                color = Color.Blue,
                strokeWidth = 10.dp
            )
        }
    }
}