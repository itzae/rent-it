package com.itgonca.rentit.utils.extension

import androidx.core.util.PatternsCompat.EMAIL_ADDRESS

fun String.isValidEmail() =  EMAIL_ADDRESS.matcher(this).matches()