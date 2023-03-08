package com.dogandpigs.fitnote.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.dogandpigs.fitnote.presentation.join.addJoin
import com.dogandpigs.fitnote.presentation.lesson.addlesson.addAddLessonScreen
import com.dogandpigs.fitnote.presentation.lesson.loadlesson.addLoadLesson
import com.dogandpigs.fitnote.presentation.lesson.memberlesson.addMemberLesson
import com.dogandpigs.fitnote.presentation.lesson.memberlessonlist.addMemberLessonListScreen
import com.dogandpigs.fitnote.presentation.login.addLogin
import com.dogandpigs.fitnote.presentation.login.addLoginWithEmail
import com.dogandpigs.fitnote.presentation.member.memberadd.addMemberAdd
import com.dogandpigs.fitnote.presentation.member.memberdetail.addMemberDetail
import com.dogandpigs.fitnote.presentation.member.memberedit.addMemberEdit
import com.dogandpigs.fitnote.presentation.member.memberlist.MemberListNavRoute
import com.dogandpigs.fitnote.presentation.member.memberlist.addMemberList
import com.dogandpigs.fitnote.presentation.member.memberlist.addMemberListWithRegistration
import com.dogandpigs.fitnote.presentation.setting.addSettingScreen
import com.dogandpigs.fitnote.presentation.share.addShare
import com.dogandpigs.fitnote.presentation.splash.addSplash

@Composable
internal fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_SPLASH
    ) {
        addSplash(
            route = ROUTE_SPLASH,
            navigateToHome = { navController.navigateToHome() },
            navigateToJoin = { navController.navigate(ROUTE_JOIN) },
            navigateToLogin = { navController.navigate(ROUTE_LOGIN) },
            navigateToLesson = { navController.navigate(ROUTE_MEMBER_LESSON_LIST) },
            navigateToMemberList = { navController.navigate(MemberListNavRoute.route) },
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
            navigateToHome = { navController.navigateToHome() }
        )
        addLoginWithEmail(
            route = "$ROUTE_LOGIN/{$ARGUMENT_EMAIL}",
            popBackStack = { navController.popBackStack() },
            navigateToHome = { navController.navigateToHome() }
        )
        addSettingScreen(
            route = ROUTE_SETTING,
            popBackStack = { navController.popBackStack() },
            navigateToSplash = {
                navController.navigate(ROUTE_SPLASH) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            },
        )
        addMember(navController)
        addLesson(navController)
        addShare(
            route = "$ROUTE_SHARE/{$ARGUMENT_MEMBER_ID}/{$ARGUMENT_LESSON_DATE}",
        )
    }
}

private fun NavGraphBuilder.addMember(
    navController: NavHostController,
) {
    addMemberList(
        route = MemberListNavRoute.route,
        navigateToMemberDetail = { navController.navigate("$ROUTE_MEMBER_DETAIL/$it") },
        navigateToMemberAdd = { navController.navigate(ROUTE_MEMBER_ADD) },
        navigateToMemberLessonList = { navController.navigate("$ROUTE_MEMBER_LESSON_LIST/$it") },
        navigateToSetting = { navController.navigate(ROUTE_SETTING) }
    )
    addMemberListWithRegistration(
        route = "${MemberListNavRoute.route}/{$ARGUMENT_REGISTRATION}",
        navigateToMemberDetail = { navController.navigate("$ROUTE_MEMBER_DETAIL/$it") },
        navigateToMemberAdd = { navController.navigate(ROUTE_MEMBER_ADD) },
        navigateToMemberLessonList = { navController.navigate("$ROUTE_MEMBER_LESSON_LIST/$it") },
        navigateToSetting = { navController.navigate(ROUTE_SETTING) }
    )
    addMemberAdd(
        route = ROUTE_MEMBER_ADD,
        navigateToHome = { navController.navigateToHome() },
        navigateToMemberListWithRegistration = {
            navController.navigate(
                route = "${MemberListNavRoute.route}/$it"
            ) {
                popUpTo(MemberListNavRoute.route) { inclusive = true }
            }
        }
    )
    addMemberEdit(
        route = "$ROUTE_MEMBER_EDIT/{$ARGUMENT_MEMBER_ID}",
        popBackStack = { navController.popBackStack() },
        navigateToMemberDetail = {
            navController.navigate(
                route = "$ROUTE_MEMBER_DETAIL/$it"
            ) {
                popUpTo("$ROUTE_MEMBER_DETAIL/{$ARGUMENT_MEMBER_ID}") { inclusive = true }
            }
        }
    )
    addMemberDetail(
        route = "$ROUTE_MEMBER_DETAIL/{$ARGUMENT_MEMBER_ID}",
        popBackStack = { navController.popBackStack() },
        navigateToMemberEdit = { navController.navigate("$ROUTE_MEMBER_EDIT/$it") },
        navigateToMemberLessonList = { navController.navigate("$ROUTE_MEMBER_LESSON_LIST/$it") },
    )
}

private fun NavGraphBuilder.addLesson(
    navController: NavHostController,
) {
    addMemberLessonListScreen(
        route = "$ROUTE_MEMBER_LESSON_LIST/{$ARGUMENT_MEMBER_ID}",
        popBackStack = { navController.popBackStack() },
        navigateToAddLesson = {
            navController.navigate("$ROUTE_ADD_LESSON/$it")
        },
        navigateToMemberLesson = { memberId, lessonDate ->
            navController.navigate("$ROUTE_MEMBER_LESSON/$memberId/$lessonDate")
        },
        navigateToShare = { memberId, lessonDate ->
            navController.navigate("$ROUTE_SHARE/$memberId/$lessonDate")
        },
    )
    addAddLessonScreen(
        route = "$ROUTE_ADD_LESSON/{$ARGUMENT_MEMBER_ID}",
        popBackStack = { navController.popBackStack() },
        navigateToLoadLesson = { navController.navigate(ROUTE_LOAD_LESSON) },
        navigateToMemberLessonList = {},
    )
    addMemberLesson(
        route = "$ROUTE_MEMBER_LESSON/{$ARGUMENT_MEMBER_ID}/{$ARGUMENT_LESSON_DATE}",
        popBackStack = { navController.popBackStack() },
        navigateToAddLesson = { navController.navigate(ROUTE_ADD_LESSON) },
        navigateToSetting = {},
    )
    addLoadLesson(
        route = ROUTE_LOAD_LESSON,
        popBackStack = { navController.popBackStack() }
    )
}
