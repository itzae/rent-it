package com.itgonca.rentit.ui.compose.screens.login

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.itgonca.rentit.R
import com.itgonca.rentit.ui.compose.components.FieldText
import com.itgonca.rentit.ui.compose.components.PasswordInput
import com.itgonca.rentit.ui.compose.theme.Grey100
import com.itgonca.rentit.ui.compose.theme.LightGrey100
import com.itgonca.rentit.ui.compose.theme.Success100
import com.itgonca.rentit.utils.extension.isValidEmail

@ExperimentalAnimationApi
@Composable
fun SectionCredentialsScreen(
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
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "Welcome back!",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "Enter your email or number",
            style = MaterialTheme.typography.caption.copy(color = Grey100)
        )

        AnimatedContent(
            targetState = step,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInHorizontally({ width -> width }) + fadeIn() with
                            slideOutHorizontally({ width -> -width }) + fadeOut()
                } else {
                    slideInHorizontally({ width -> -width }) + fadeIn() with
                            slideOutHorizontally({ width -> width }) + fadeOut()
                }.using(
                    SizeTransform(clip = false)
                )
            }) { currentStep ->
            when (currentStep) {
                1 -> {
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
                        style = MaterialTheme.typography.caption,
                        labelText = {
                            Text(
                                text = "EMAIL OR MOBILE NUMBER",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(0.dp)
                            )
                        }
                    )
                }
                2 -> {

                    PasswordInput(
                        password = password,
                        onPasswordChange = { onPasswordChange(it) },
                        modifier = Modifier.fillMaxWidth(),
                        messageError = stringResource(id = R.string.invalid_password_label),
                        placeholder = stringResource(id = R.string.password_label)
                    )

                }
            }
        }


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = { if (step == 1) onStepChange(2) else onStepChange(1) },
            shape = RoundedCornerShape(60.dp),
            enabled = if (step == 1) email.isValidEmail() else email.isNotEmpty() && password.isNotEmpty()
        ) {
            Text(
                text = if (step == 1) "Next step" else "Login",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.h4.copy(color = Color.White)
            )
        }
        Text(
            text = "or",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption.copy(color = Grey100)
        )
        OutlinedTextField(value = "", onValueChange = {

        })
        OutlinedButton(
            onClick = {
                if (step == 1)
                    onStepChange(3)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(60.dp), border = BorderStroke(2.dp, LightGrey100)
        ) {
            Text(
                text = if (step == 1) "Create an account" else "Forgot password",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.h4
            )
        }

    }
}