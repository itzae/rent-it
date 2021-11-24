package com.itgonca.rentit.utils.extension

import android.graphics.drawable.Drawable
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.itgonca.rentit.ui.MainActivity

fun Fragment.showAlert(title: String, message: String) {
    MaterialAlertDialogBuilder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("Ok") { _, _ ->

        }.show()
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
fun Fragment.activityContext() = (activity as MainActivity)

fun Fragment.resourceDrawable(res: Int): Drawable? =
    ContextCompat.getDrawable(requireContext(), res)
