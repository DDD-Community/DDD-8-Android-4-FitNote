package com.dogandpigs.fitnote.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.dogandpigs.fitnote.presentation.join.addJoin
import com.dogandpigs.fitnote.presentation.lesson.addlesson.addAddLesson
import com.dogandpigs.fitnote.presentation.lesson.loadlesson.addLoadLesson
import com.dogandpigs.fitnote.presentation.lesson.memberlesson.addMemberLesson
import com.dogandpigs.fitnote.presentation.lesson.memberlessonlist.addMemberLessonList
import com.dogandpigs.fitnote.presentation.login.addLogin
import com.dogandpigs.fitnote.presentation.login.addLoginWithEmail
import com.dogandpigs.fitnote.presentation.member.memberadd.addMemberAdd
import com.dogandpigs.fitnote.presentation.member.memberlist.addMemberList
import com.dogandpigs.fitnote.presentation.member.memberlist.addMemberListWithRegistration
import com.dogandpigs.fitnote.presentation.splash.addSplash

@Composable
internal fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_SPLASH
    ) {
        addSplash(
            route = ROUTE_SPLASH,
            navigateToHome = {
                navController.navigate(ROUTE_HOME) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            },
            navigateToJoin = { navController.navigate(ROUTE_JOIN) },
            navigateToLogin = { navController.navigate(ROUTE_LOGIN) },
            navigateToLesson = { navController.navigate(ROUTE_LESSON) },
            navigateToMemberList = { navController.navigate(ROUTE_MEMBER_LIST) },
            navigateToMemberLesson = { navController.navigate(ROUTE_MEMBER_LESSON) },
        )
        addJoin(
            route = ROUTE_JOIN,
            popBackStack = { navController.popBackStack() },
            navigateToLogin = {
                navController.navigate(
                    route = "$ROUTE_LOGIN/$it"
                ) {
                    popUpTo(ROUTE_JOIN) { inclusive = true }
                }
            }
        )
        addLogin(
            route = ROUTE_LOGIN,
            popBackStack = { navController.popBackStack() },
            navigateToHome = {
                navController.navigate(ROUTE_ADD_LESSON) {
//                navController.navigate(ROUTE_HOME) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        )
        addLoginWithEmail(
            route = "$ROUTE_LOGIN/{$ARGUMENT_EMAIL}",
            popBackStack = { navController.popBackStack() },
            navigateToHome = { navController.navigate(ROUTE_HOME) }
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
            navigateToMemberAdd = { navController.navigate(ROUTE_MEMBER_ADD) },
            navigateToLesson = {},
            navigateToSetting = {}
        )
        addMemberListWithRegistration(
            route = "$ROUTE_MEMBER_LIST/{$ARGUMENT_REGISTRATION}",
            popBackStack = { navController.popBackStack() },
            navigateToMemberDetail = {},
            navigateToMemberAdd = { navController.navigate(ROUTE_MEMBER_ADD) },
            navigateToLesson = {},
            navigateToSetting = {}
        )
        addMemberAdd(
            route = ROUTE_MEMBER_ADD,
            popBackStack = { navController.popBackStack() },
            navigateToMemberListWithRegistration = {
                navController.navigate(
                    route = "$ROUTE_MEMBER_LIST/$it"
                ) {
                    popUpTo(ROUTE_MEMBER_ADD) { inclusive = true }
                }
            }
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
