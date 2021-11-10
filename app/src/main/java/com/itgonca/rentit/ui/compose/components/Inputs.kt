package com.itgonca.rentit.ui.compose.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.itgonca.rentit.ui.compose.theme.LightGrey100

@Composable
fun FieldText(
    modifier: Modifier = Modifier,
    valueText: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    isErrorEnabled: Boolean = false,
    visualTransformationField: VisualTransformation = VisualTransformation.None,
    style: TextStyle = TextStyle(),
    iconStart: (@Composable () -> Unit)? = null,
    iconEnd: (@Composable () -> Unit)? = null,
    labelText: @Composable () -> Unit = {}
) {
    TextField(
        value = valueText,
        onValueChange = onTextChange,
        placeholder = { Text(text = placeholder, color = MaterialTheme.colors.onSurface) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
        trailingIcon = iconEnd,
        leadingIcon = iconStart,
        modifier = modifier,
        isError = isErrorEnabled,
        label = labelText,
        visualTransformation = visualTransformationField,
        textStyle = style,
    )
}


@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    password: String,
    messageError: String = "",
    placeholder: String = "",
    isErrorPasswordEnabled: Boolean = false,
    onPasswordChange: (String) -> Unit
) {
    var isPasswordShow by remember { mutableStateOf(false) }

    LabelHelper(
        message = if (isErrorPasswordEnabled) messageError else "",
        modifier = Modifier.padding(top = 16.dp),
        colorMessage = MaterialTheme.colors.error,
        isHelperShow = isErrorPasswordEnabled
    ) {
        FieldText(
            modifier = modifier,
            valueText = password,
            onTextChange = onPasswordChange,
            placeholder = placeholder,
            iconEnd = {
                IconButton(onClick = { isPasswordShow = !isPasswordShow }) {
                    Icon(
                        if (isPasswordShow) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = "Password toggle"
                    )
                }
            },
            visualTransformationField =
            if (!isPasswordShow) PasswordVisualTransformation('\u26AB') else VisualTransformation.None,
            style = MaterialTheme.typography.caption.copy(color = LightGrey100)
        )
    }
}