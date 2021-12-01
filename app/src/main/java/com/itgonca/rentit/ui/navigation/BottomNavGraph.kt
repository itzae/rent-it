package com.itgonca.rentit.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.itgonca.rentit.ui.compose.screens.favorites.FavoritesScreen
import com.itgonca.rentit.ui.compose.screens.home.HomeScreen
import com.itgonca.rentit.ui.viewmodel.HomeViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    innerPaddingValues: PaddingValues
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val listLocations by viewModel.listLocations.collectAsState()
    NavHost(navController = navController, startDestination = BottomBarScreens.Home.route) {
        composable(route = BottomBarScreens.Home.route) {
            HomeScreen(
                listLocations = listLocations,
                modifier = Modifier.padding(innerPaddingValues)
            ) { id, isFavorite ->
                viewModel.updateFavorite(id, isFavorite)
            }
        }
        composable(route = BottomBarScreens.Favorites.route) {
            val listLocationsFavorites = listLocations.filter { it.favorite }

            FavoritesScreen(
                modifier = Modifier.padding(innerPaddingValues),
                listLocations = listLocationsFavorites
            ) { id, isFavorite ->
                viewModel.updateFavorite(id, isFavorite)
            }
        }
        composable(route = BottomBarScreens.Chat.route) {}
        composable(route = BottomBarScreens.Profile.route) {}
    }
}