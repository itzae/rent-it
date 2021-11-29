package com.itgonca.rentit.ui.compose.screens

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itgonca.rentit.R
import com.itgonca.rentit.ui.compose.screens.login.SectionCredentialsScreen
import com.itgonca.rentit.ui.compose.screens.login.SignUpScreen
import com.itgonca.rentit.ui.compose.theme.RentItTheme

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun LoginScreen(
    email: String,
    password: String,
    step: Int = 1,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onStepChange: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy((-30).dp)
    ) {

        Crossfade(
            targetState = step, modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) { stepCurrent ->
            when (stepCurrent) {
                1, 2 -> Image(
                    painter = painterResource(id = R.drawable.login_bg),
                    contentDescription = "Login Image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
                3 -> Image(
                    painter = painterResource(id = R.drawable.banner_sign_up),
                    contentDescription = "Login Image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
            }

        }

        Card(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(Color.Transparent),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            AnimatedContent(
                targetState = step,
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInHorizontally({ width -> if (step != 2) -width else width }) + fadeIn() with
                                slideOutHorizontally({ width -> if (step != 2) width else -width }) + fadeOut()
                    } else {
                        slideInHorizontally({ width -> if (step != 2) width else -width }) + fadeIn() with
                                slideOutHorizontally({ width -> if (step != 2) -width else width }) + fadeOut()
                    }.using(
                        SizeTransform(clip = false)
                    )
                }) { currentStep ->
                when (currentStep) {
                    1, 2 -> SectionCredentialsScreen(
                        email = email,
                        password = password,
                        step = step,
                        onPasswordChange = onPasswordChange,
                        onEmailChange = onEmailChange,
                        onStepChange = onStepChange
                    )
                    3 -> SignUpScreen(
                        email = email,
                        password = password,
                        onEmailChange = onEmailChange,
                        onPasswordChange = onPasswordChange,
                        onStepChange = onStepChange
                    )
                }
            }
        }
    }
}

//Preview of screen login

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview(name = "LoginNexus5", showBackground = true, device = Devices.NEXUS_5)
@Composable
fun PreviewLoginOnNexus5() {
    RentItTheme {
        LoginScreen(
            email = stringResource(id = R.string.example_preview_label),
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            onStepChange = {}
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview(name = "LoginNexus6", showBackground = true, device = Devices.NEXUS_6)
@Composable
fun PreviewLoginOnNexus6() {
    RentItTheme {
        LoginScreen(
            email = stringResource(id = R.string.example_preview_label),
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            onStepChange = {}
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview(name = "LoginPixel3a", showBackground = true, device = Devices.PIXEL_3A_XL)
@Composable
fun PreviewLoginOnPixel3a() {
    RentItTheme {
        LoginScreen(
            email = stringResource(id = R.string.example_preview_label),
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            onStepChange = {}
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview(name = "CreateAccountPixel3a", showBackground = true, device = Devices.PIXEL_3A_XL)
@Composable
fun PreviewCreateAccountOnPixel3a() {
    RentItTheme {
        LoginScreen(
            email = stringResource(id = R.string.example_preview_label),
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            onStepChange = {},
            step = 3
        )
    }
}