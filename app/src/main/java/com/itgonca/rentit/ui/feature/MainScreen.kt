package com.itgonca.rentit.ui.feature

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itgonca.rentit.R
import com.itgonca.rentit.ui.compose.components.SearchBarWithButton
import com.itgonca.rentit.ui.compose.state.rememberEditableInputState
import com.itgonca.rentit.ui.compose.theme.Blue100
import com.itgonca.rentit.ui.compose.theme.Grey100
import com.itgonca.rentit.ui.navigation.BottomBarScreens
import com.itgonca.rentit.ui.navigation.BottomNavGraph
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter

@Composable
fun MainScreen(
    listFilters: List<String>,
    onQueryChange: (String) -> Unit
) {
    val navController = rememberNavController()
    val searchBarState =
        rememberEditableInputState(hint = stringResource(id = R.string.search_city_label))

    val currentOnQueryChanged by rememberUpdatedState(onQueryChange)
    LaunchedEffect(searchBarState) {
        snapshotFlow { searchBarState.text }
            .filter { !searchBarState.isHint }
            .collect {
                currentOnQueryChanged(searchBarState.text)
            }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = 0.dp,
                modifier = Modifier.height(120.dp)
            ) {
                SearchBarWithButton(searchBarState, listFilters = listFilters)
            }
        },
        content = { innerPadding ->
            BottomNavGraph(navController = navController, innerPadding)
        },
        bottomBar = {
            BottomBar(
                navController = navController
            )
        })
}

@Composable
fun BottomBar(navController: NavHostController) {
    val listScreens = listOf(
        BottomBarScreens.Home,
        BottomBarScreens.Favorites,
        BottomBarScreens.Chat,
        BottomBarScreens.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation {
        listScreens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreens,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val isSelected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true
    BottomNavigationItem(
        selected = isSelected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = screen.iconSelected),
                contentDescription = null
            )
        },
        unselectedContentColor = Grey100,
        selectedContentColor = Blue100
    )
}

@Preview(name = "MainScreenPreview", showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        listFilters = listOf("Filtro 1", "Filtro 2", "Filtro 3", "Filtro 4"),
        onQueryChange = {})
}