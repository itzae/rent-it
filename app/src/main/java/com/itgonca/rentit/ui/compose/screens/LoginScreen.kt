package com.itgonca.rentit.ui.compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itgonca.rentit.R
import com.itgonca.rentit.ui.compose.theme.RentItTheme

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
        Image(
            painter = painterResource(id = R.drawable.login_bg),
            contentDescription = "Login Image",
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentScale = ContentScale.FillWidth
        )
        Card(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(Color.Transparent),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            SectionCredentialsScreen(
                email = email,
                password = password,
                step = step,
                onPasswordChange = onPasswordChange,
                onEmailChange = onEmailChange,
                onStepChange = onStepChange
            )
        }
    }
}

@Preview(name = "Login",showBackground = true )
@Composable
fun PreviewLogin(){
    RentItTheme {
        LoginScreen(
            email = "",
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            onStepChange = {}
        )
    }
}