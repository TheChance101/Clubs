package com.devfalah.ui.screen.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devfalah.ui.screen.SearchScreen

class SearchRoute {
}

private const val ROUTE_SEARCH = "search"
fun NavGraphBuilder.searchRoute(navController: NavController){
    composable(route = ROUTE_SEARCH){
        SearchScreen(navController)
    }
}