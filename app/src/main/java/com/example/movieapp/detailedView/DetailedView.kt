package com.example.movieapp.detailedView

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.utils.Constants

@Composable
fun DetailedView(mealId: String,
                 viewModel: DetailedViewModel = DetailedViewModel(mealId)) {
    val context = LocalContext.current



    val meal by viewModel.mealLiveData.observeAsState()

    if (meal != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .background(colorResource(R.color.movie_detailed_view_bg))
                .background(color = Color.Black)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TopNavigation("MOVIES")
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Name(name = meal!!.title)
                Spacer(Modifier.weight(1f))
                Row(modifier = Modifier) {
                    Icon(
                        Icons.Default.Edit,
                        "Edit",
                        Modifier.clickable {
                        }.height(35.dp).width(35.dp),
                        Color.Blue,
                    )
                    Icon(
                        Icons.Default.Delete,
                        "Delete",
                        Modifier.clickable {
//                            run { viewModel.deleteOneMealById(mealId) }
//                            context.startActivity(Intent(context, MainActivity::class.java))
                        }.height(35.dp).width(35.dp),
                        Color.Red
                    )
                }
            }
            AsyncImage(
                model = Constants.IMGURL + meal!!.poster_path,
                contentDescription = meal!!.title,
                modifier = Modifier.fillMaxWidth(1f)
            )
            Spacer(Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth(1f)) {
                Category(name = meal!!.overview)
                Spacer(modifier = Modifier.weight(1f))
                Country(country = meal!!.original_language)
            }
            Spacer(Modifier.height(10.dp))
//            RedirectLink(meal!!.youtubeVideoUrl)
            Spacer(Modifier.height(10.dp))
            Description(description = meal!!.original_title)
            Spacer(Modifier.height(16.dp))
//            if (!meal!!.recipes.isNullOrEmpty()) {
//                Recipes(recipes = meal!!.recipes!!)
//            }
        }
    }
}

@Composable
private fun Name(name: String) {
    Text(
        text = name,
        color = Color.Black,
        fontSize = 30.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun Description(description: String) {
    Text(
        modifier = Modifier.padding(top = 10.dp),
        text = description,
        color = Color.DarkGray,
        fontSize = 22.sp,
        fontFamily = FontFamily.SansSerif
    )
}

@Composable
private fun Country(country: String) {
    Text(
        modifier = Modifier.padding(vertical = 10.dp),
        text = country,
        color = Color.Black,
        fontSize = 26.sp,
        fontFamily = FontFamily.Serif
    )
}

@Composable
fun Category(name: String) {
    Text(
        modifier = Modifier.padding(top = 10.dp),
        text = name,
        color = Color.DarkGray,
        fontSize = 22.sp,
        fontFamily = FontFamily.Monospace
    )
}


@Composable
private fun Recipes(recipes: List<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        var i = 0
        for (recipe in recipes) {
            ActorTextView(recipe = recipe, ++i == recipes.size)
        }
    }
}

@Composable
private fun ActorTextView(recipe: String, isTheLastOne: Boolean) {
    Text(
        modifier = Modifier.padding(6.dp, 3.dp),
        text = if (isTheLastOne) recipe else "$recipe,",
        color = Color.DarkGray,
        fontSize = 19.sp,
        fontFamily = FontFamily.SansSerif,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun TopNavigation(text:String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
        )
    }
    Divider(
        color = Color.Blue
    )
}

//@Composable
//private fun DeleteButton(onClick: () -> Unit, context: Context) {
//    Button(
//        onClick = {
//            onClick()
////            context.startActivity(Intent(context, MainActivity::class.java))
//        },
//        modifier = Modifier
//            .width(150.dp)
//            .height(75.dp)
//            .padding(vertical = 16.dp)
//    ) {
//        Text(
////            text = stringResource(id = R.string.youtube_video_button_text)
//        )
//    }
//}

//@Composable
//private fun RedirectLink(link: String) {
//    val uriHandler = LocalUriHandler.current
//    Button(
//        onClick = {
//            uriHandler.openUri(link)
//        },
//        modifier = Modifier
//            .width(200.dp)
//            .height(100.dp)
//            .padding(vertical = 16.dp)
//    ) {
//        Text(
////            text = stringResource(id = R.string.youtube_video_button_text)
//            text = stringResource(id = R.string.youtube_video_button_text)
//        )
//    }
//}
