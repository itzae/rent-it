package com.itgonca.rentit.data.remote.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Locations(val Locations: List<Location> = listOf())