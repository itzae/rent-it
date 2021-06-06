package com.itgonca.rentit.data.remote.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Location(val name: String = "", val price: Double= 0.0){
}