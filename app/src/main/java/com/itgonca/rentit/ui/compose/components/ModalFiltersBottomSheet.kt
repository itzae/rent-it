package com.itgonca.rentit.ui.compose.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope

@ExperimentalMaterialApi
@Composable
fun ModalFiltersBottomSheet(
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    content: @Composable ()->Unit
) {
    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            LazyColumn() {
                items(25) {
                    Text(text = "$it")
                }
            }
        },
    ) {
        content()
    }
}