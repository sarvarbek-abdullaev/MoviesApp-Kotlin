package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.detailedView.DetailedView
import com.example.movieapp.list.MoviesList
import com.example.movieapp.settings.Settings
import com.example.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                    contentColor = Color.DarkGray
                ) {
                    val navController = rememberNavController()
                    Scaffold(bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Home",
                                    route = "mainList",
                                    icon = Icons.Default.List
                                ),
                                BottomNavItem(
                                    name = "Movies",
                                    route = "moviesList",
                                    icon = Icons.Default.List
                                ),
                                BottomNavItem(
                                    name = "Cartoons",
                                    route = "cartoonsList",
                                    icon = Icons.Default.List
                                ),
                                BottomNavItem(
                                    name = "Popular",
                                    route = "popularList",
                                    icon = Icons.Default.List
                                ),
                                BottomNavItem(
                                    name = "Settings",
                                    route = "settings",
                                    icon = Icons.Default.Settings
                                )

                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "mainList") {
        composable("mainList") {
            MoviesList(
                genre = "main",
                onMovieClick = { movieId ->
                    navController.navigate("detailedView/$movieId") }
            )
        }
        composable("moviesList") {
            MoviesList(
                genre = "movies",
                onMovieClick = { movieId ->
                    navController.navigate("detailedView/$movieId") }
            )
        }
        composable("cartoonsList") {
            MoviesList(
                genre = "cartoons",
                onMovieClick = { movieId ->
                    navController.navigate("detailedView/$movieId") }
            )
        }
        composable("popularList") {
            MoviesList(
                genre = "popular",
                onMovieClick = { movieId ->
                    navController.navigate("detailedView/$movieId") }
            )
        }

        composable("settings") {
            Settings()
        }

        composable(
            "detailedView/{movieId}"
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("movieId")?.let {
                DetailedView(mealId = it)
            }
        }

    }
}


@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.LightGray,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Blue,
                unselectedContentColor = Color.DarkGray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(imageVector = item.icon, contentDescription = item.name)
                        Text(
                            text = item.name,
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp
                        )
                    }
                }
            )
        }
    }
}