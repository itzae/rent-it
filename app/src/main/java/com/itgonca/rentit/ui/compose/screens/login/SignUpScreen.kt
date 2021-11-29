package com.itgonca.rentit.ui.compose.screens.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.itgonca.rentit.R
import com.itgonca.rentit.ui.compose.components.FieldText
import com.itgonca.rentit.ui.compose.components.PasswordInput
import com.itgonca.rentit.ui.compose.theme.Grey100
import com.itgonca.rentit.ui.compose.theme.Success100
import com.itgonca.rentit.utils.extension.isValidEmail

@ExperimentalAnimationApi
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    step: Int = 1,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onStepChange: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "Create new account",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "Fill those form and jump to next step",
            style = MaterialTheme.typography.caption.copy(color = Grey100)
        )
        Text(
            text = "EMAIL",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(top = 16.dp)
        )

        FieldText(
            modifier = Modifier
                .fillMaxWidth(),
            valueText = email,
            onTextChange = onEmailChange,
            placeholder = "example@example.com",
            iconEnd = if (email.isValidEmail()) {
                {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_green_check_circle),
                        contentDescription = "Check email", tint = Success100
                    )
                }
            } else null,
            style = MaterialTheme.typography.caption
        )

        Text(
            text = "PASSWORD",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(top = 16.dp)
        )

        PasswordInput(
            password = password,
            onPasswordChange = { onPasswordChange(it) },
            modifier = Modifier.fillMaxWidth(),
            messageError = stringResource(id = R.string.invalid_password_label),
            placeholder = stringResource(id = R.string.password_label)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = { onStepChange(1) },
            shape = RoundedCornerShape(60.dp),
            enabled = if (step == 1) email.isValidEmail() else email.isNotEmpty() && password.isNotEmpty()
        ) {
            Text(
                text = "Next step",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.h4.copy(color = Color.White)
            )
        }
    }
}