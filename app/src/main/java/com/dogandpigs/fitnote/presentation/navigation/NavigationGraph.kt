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
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dogandpigs.fitnote.presentation.join.addJoin
import com.dogandpigs.fitnote.presentation.lesson.addlesson.addAddLesson
import com.dogandpigs.fitnote.presentation.lesson.loadlesson.addLoadLesson
import com.dogandpigs.fitnote.presentation.lesson.memberlesson.addMemberLesson
import com.dogandpigs.fitnote.presentation.lesson.memberlessonlist.addMemberLessonList
import com.dogandpigs.fitnote.presentation.memberlist.addMemberList
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes.Companion.ROUTE_ADD_LESSON
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes.Companion.ROUTE_JOIN
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes.Companion.ROUTE_LESSON
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes.Companion.ROUTE_LOAD_LESSON
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes.Companion.ROUTE_LOGIN
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes.Companion.ROUTE_MEMBER_LESSON
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes.Companion.ROUTE_MEMBER_LIST
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes.Companion.ROUTE_SPLASH
import com.dogandpigs.fitnote.presentation.splash.LoginRoute
import com.dogandpigs.fitnote.presentation.splash.addSplash

@Composable
internal fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_SPLASH
    ) {
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
            navigateToHome = {
                // TODO 수정 필요
                // navController.navigate(NavRoutes.Home.route)
                navController.navigate(ROUTE_MEMBER_LIST) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            },
            navigateToJoin = { navController.navigate(NavRoutes.Join.route) },
            navigateToLogin = { navController.navigate(NavRoutes.Login.route) },
            navigateToLesson = { navController.navigate(NavRoutes.Lesson.route) },
            navigateToMemberList = { navController.navigate(ROUTE_MEMBER_LIST) },
            navigateToMemberLesson = { navController.navigate(ROUTE_MEMBER_LESSON) },
        )
        addJoin(
            route = ROUTE_JOIN,
            popBackStack = { navController.popBackStack() }
        )
        addLogin(
            route = ROUTE_LOGIN,
            navigateToHome = { navController.navigate(NavRoutes.Home.route) }
        )
        addMemberLessonList(
            route = ROUTE_LESSON,
            popBackStack = { navController.popBackStack() },
            navigateToAddLesson = { navController.navigate(ROUTE_ADD_LESSON) },
            navigateToSetting = {},
        )
        addAddLesson(
            route = ROUTE_ADD_LESSON,
            popBackStack = { navController.popBackStack() },
            navigateToLoadLesson = { navController.navigate(ROUTE_LOAD_LESSON) },
            navigateToAddExercise = {},
        )
        addMemberList(
            route = ROUTE_MEMBER_LIST,
            popBackStack = { navController.popBackStack() },
            navigateToMemberDetail = {},
            navigateToAddMember = {},
            navigateToLesson = {},
            navigateToSetting = {}
        )
        addMemberLesson(
            route = ROUTE_MEMBER_LESSON,
            popBackStack = { navController.popBackStack() },
            navigateToAddLesson = { navController.navigate(ROUTE_ADD_LESSON) },
            navigateToSetting = {},
        )
        addLoadLesson(
            route = ROUTE_LOAD_LESSON,
            popBackStack = { navController.popBackStack() }
        )
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

fun NavController.navigateToScreen(navOptions: NavOptions? = null) {
    this.navigate(ROUTE_SPLASH, navOptions)
}
