package com.dogandpigs.fitnote.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dogandpigs.fitnote.presentation.lesson.addLesson
import com.dogandpigs.fitnote.presentation.lesson.addlesson.AddLessonScreen
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes.Companion.ROUTE_JOIN
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes.Companion.ROUTE_LESSON
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes.Companion.ROUTE_LOGIN
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes.Companion.ROUTE_SPLASH
import com.dogandpigs.fitnote.presentation.splash.JoinRoute
import com.dogandpigs.fitnote.presentation.splash.LoginRoute
import com.dogandpigs.fitnote.presentation.splash.SplashScreen
import com.dogandpigs.fitnote.presentation.splash.SplashViewModel

@Composable
internal fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_SPLASH
    ) {
        composable(NavRoutes.Home.route) {
            HomePage()
        }
        composable(NavRoutes.FirstPage.route) {
            FirstPage()
        }
        composable(NavRoutes.SecondPage.route) {
            SecondPage()
        }
        composable(NavRoutes.ThirdPage.route) {
            ThirdPage()
        }
        addSplash(
            route = ROUTE_SPLASH,
            navigateToHome = { navController.navigate(NavRoutes.Home.route) },
            navigateToJoin = { navController.navigate(NavRoutes.Join.route) },
            navigateToLogin = { navController.navigate(NavRoutes.Login.route) },
            navigateToLesson = { navController.navigate(NavRoutes.Lesson.route) },
        )
        addJoin(
            route = ROUTE_JOIN,
            navigateToHome = { navController.navigate(NavRoutes.Home.route) }
        )
        addLogin(
            route = ROUTE_LOGIN,
            navigateToHome = { navController.navigate(NavRoutes.Home.route) }
        )
        addLesson(
            route = ROUTE_LESSON,
            popBackStack = { navController.popBackStack() },
            navigateToSetting = {},
        )
    }
}

@Composable
private fun HomePage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AddLessonScreen(
            viewModel = hiltViewModel(),
            navigateToHome = {},
            navigateToLoad = {})
    }
}

@Composable
private fun FirstPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "FirstScreen",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun SecondPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "SecondScreen",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun ThirdPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "ThirdScreen",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

fun NavGraphBuilder.addSplash(
    route: String,
    navigateToHome: () -> Unit,
    navigateToJoin: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToLesson: () -> Unit,
) {
    composable(route = route) {
        SplashRoute(
            navigateToHome = navigateToHome,
            navigateToJoin = navigateToJoin,
            navigateToLogin = navigateToLogin,
            navigateToLesson = navigateToLesson,
        )
    }
}

fun NavGraphBuilder.addJoin(
    route: String,
    navigateToHome: () -> Unit,
) {
    composable(route = route) {
        JoinRoute(
            navigateToHome = navigateToHome
        )
    }
}

fun NavGraphBuilder.addLogin(
    route: String,
    navigateToHome: () -> Unit,
) {
    composable(route = route) {
        LoginRoute(
            navigateToHome = navigateToHome
        )
    }
}

@Composable
internal fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToJoin: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToLesson: () -> Unit,
) {
    SplashScreen(
        viewModel = viewModel,
        navigateToHome = navigateToHome,
        navigateToJoin,
        navigateToLogin,
        navigateToLesson = navigateToLesson,
    )
}

fun NavController.navigateToScreen(navOptions: NavOptions? = null) {
    this.navigate(ROUTE_SPLASH, navOptions)
}
