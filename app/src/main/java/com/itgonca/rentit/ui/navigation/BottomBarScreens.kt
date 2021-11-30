package com.itgonca.rentit.ui.navigation

import com.itgonca.rentit.R

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val iconSelected: Int,
    val iconUnSelected: Int
) {
    object Home : BottomBarScreens(
        route = "home",
        title = "Home",
        iconSelected = R.drawable.ic_blue_home,
        iconUnSelected = R.drawable.ic_home
    )

    object Favorites : BottomBarScreens(
        route = "favorites",
        title = "Favorites",
        iconSelected = R.drawable.ic_blue_favorite,
        iconUnSelected = R.drawable.ic_favorites
    )

    object Chat : BottomBarScreens(
        route = "chat",
        title = "Chat",
        iconSelected = R.drawable.ic_blue_chat,
        iconUnSelected = R.drawable.ic_messages
    )

    object Profile : BottomBarScreens(
        route = "profile",
        title = "Profile",
        iconSelected = R.drawable.ic_blue_user,
        iconUnSelected = R.drawable.ic_user
    )
}