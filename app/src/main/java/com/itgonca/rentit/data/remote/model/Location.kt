package com.itgonca.rentit.data.remote.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Location(
    var id: Int = 0,
    val name: String = "",
    val price: Double = 0.0,
    val image: String = "",
    val likes: Int = 0,
    val isFavorite: Boolean = false,
    val checkboxPreviousState: Boolean = false,
    var checkboxCurrentState: Boolean = checkboxPreviousState
)