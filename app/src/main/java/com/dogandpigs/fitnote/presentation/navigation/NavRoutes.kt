package com.dogandpigs.fitnote.presentation.navigation

import com.dogandpigs.fitnote.R

/**
 * NavigationBar에 들어갈 속성들, 추후 title, icon 등 확정되면 수정해야 함.
 */
internal sealed class NavRoutes(
    val route: String,
    val icon: Int,
    val titleRes: Int
) {
    object Home : NavRoutes(
        ROUTE_NAME_HOME,
        R.drawable.ic_fitness_center,
        R.string.title_home
    )

    object FirstPage : NavRoutes(
        ROUTE_NAME_FIRST,
        R.drawable.ic_fitness_center,
        R.string.title_first
    )

    object SecondPage : NavRoutes(
        ROUTE_NAME_SECOND,
        R.drawable.ic_fitness_center,
        R.string.title_second
    )

    object ThirdPage : NavRoutes(
        ROUTE_NAME_THIRD,
        R.drawable.ic_fitness_center,
        R.string.title_third
    )
    
    object Join : NavRoutes(
        ROUTE_JOIN,
        R.drawable.ic_fitness_center,
        0
    )
    
    object Login : NavRoutes(
        ROUTE_LOGIN,
        R.drawable.ic_fitness_center,
        0
    )

    object Lesson : NavRoutes(
        ROUTE_LESSON,
        R.drawable.ic_fitness_center,
        0
    )

    companion object {
        const val ROUTE_NAME_HOME = "route_home"
        const val ROUTE_NAME_FIRST = "route_first"
        const val ROUTE_NAME_SECOND = "route_second"
        const val ROUTE_NAME_THIRD = "route_third"
        const val ROUTE_SPLASH = "route_splash"
        const val ROUTE_JOIN = "route_join"
        const val ROUTE_LOGIN = "route_login"
        const val ROUTE_LESSON = "route_lesson"
    }
}
