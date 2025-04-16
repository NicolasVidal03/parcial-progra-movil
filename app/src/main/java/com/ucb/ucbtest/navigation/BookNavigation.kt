package com.ucb.ucbtest.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ucb.domain.Movie
import com.ucb.ucbtest.book.BookUI
import com.ucb.ucbtest.book.LikedBooksUI
import com.ucb.ucbtest.counter.CounterUI
import com.ucb.ucbtest.gitalias.GitaliasUI
import com.ucb.ucbtest.login.LoginUI
import com.ucb.ucbtest.movie.MoviesUI
import com.ucb.ucbtest.moviedetail.MovieDetailUI
import com.ucb.ucbtest.takephoto.TakePhotoUI
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun BookNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.BooksScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }

    ) {
        composable(Screen.BooksScreen.route) {
            BookUI(navController = navController)
        }
        composable(Screen.LikedBooksScreen.route) {
            LikedBooksUI(onBackPressed = {navController.popBackStack()})
        }
    }
}