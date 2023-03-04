package com.example.movieapp.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.models.Movie
import com.example.movieapp.utils.Constants

@Composable
fun MoviesList(
//    onMovieClick: (String) -> Unit = {},
    viewModel: ListViewModel = ListViewModel()
) {
    val context = LocalContext.current

    val isProgressVisible = remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {

        val movies by viewModel.moviesLiveData.observeAsState()
//        if(isProgressVisible.value) {
            CustomCircularProgressBar()
//        }
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .background(
//                    colorResource(R.color.movie_list_bg)
                    color = Color.Gray
                )
                .padding(0.dp, 0.dp, 0.dp, 50.dp),
        ) {
            movies?.let {
                items(items = it.toList(), itemContent = { item ->
//                    MovieList(movie = item, onMovieClick = onMovieClick)
                    MovieList(movie = item)
                })
            }
        }
    }
}

@Composable
fun MovieList(movie: Movie) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 10.dp, 15.dp, 0.dp)
//            .clickable {
//                onMovieClick(movie.id.toString())
//            }
    ) {
        AsyncImage(
            model = Constants.IMGURL + movie!!.poster_path,
            contentDescription = movie!!.title,
            modifier = Modifier.fillMaxWidth(1f)
        )
        Name(name = movie.title)

//        Category(name = movie.original_language)
//        Description(description = movie.description)
        Divider(
            modifier = Modifier
                .padding(top = 10.dp)
                .background(Color.Black)
        )
    }
}

@Composable
fun Name(name: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = name,
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun CustomCircularProgressBar(){
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
            strokeWidth = 10.dp)
    }

}