package com.itgonca.rentit.ui.compose.state

import androidx.compose.runtime.*

class EditableInputState(private val hint: String, initialText: String) {
    var text by mutableStateOf(initialText)

    val isHint: Boolean
        get() = text == hint
}

@Composable
fun rememberEditableInputState(hint: String): EditableInputState =
    remember(hint) {
        EditableInputState(hint, hint)
    }