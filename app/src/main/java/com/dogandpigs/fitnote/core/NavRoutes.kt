package com.dogandpigs.fitnote.core

import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.MainActivity

/**
 * NavigationBar에 들어갈 속성들, 추후 title, icon 등 확정되면 수정해야 함.
 */

sealed class NavRoutes(val route: String, val icon: Int, val titleRes: Int) {
    
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
    
    companion object {
        const val ROUTE_NAME_HOME = "route_home"
        const val ROUTE_NAME_FIRST = "route_first"
        const val ROUTE_NAME_SECOND = "route_second"
        const val ROUTE_NAME_THIRD = "route_third"
    }
}
