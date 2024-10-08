package com.example.fooddeliveryapp.authentication.login.presentation.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.authentication.components.Password
import com.example.fooddeliveryapp.authentication.components.RegOrLoginDuo
import com.example.fooddeliveryapp.authentication.components.RegisterButton
import com.example.fooddeliveryapp.authentication.components.UserName
import com.example.fooddeliveryapp.authentication.login.presentation.viewmodel.LoginViewModel

@Composable
fun Login(navController: NavController){
    val viewModel:LoginViewModel = hiltViewModel()
    val userNameText by viewModel.userNameText.collectAsState()
    val passwordText by viewModel.passwordText.collectAsState()
    val topBias = 1.15f / 3.5f // 1x distance to top

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (username,password,normalLogin,googleLogin,registerRow,orText) = createRefs()

        Button(onClick = viewModel::onLoginClick, modifier = Modifier.constrainAs(googleLogin){
            top.linkTo(parent.top)
            bottom.linkTo(username.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Icon(painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = "",
                modifier = Modifier.size(32.dp),
                tint = Color.Unspecified,
            )
        }

        Text("Or use email", fontSize = 14.sp,modifier = Modifier.constrainAs(orText){
            top.linkTo(googleLogin.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)

        })
        UserName(userNameText = userNameText,
            function = viewModel::onUserNameChange,
            modifier = Modifier.constrainAs(username){
                linkTo(top = parent.top, bottom = parent.bottom, bias = topBias)
                start.linkTo(parent.start,margin=16.dp)
                end.linkTo(parent.end,margin=16.dp)
                width = Dimension.fillToConstraints })

        Password(passwordText,
            viewModel::onPasswordChange,
            Modifier.constrainAs(password){
            top.linkTo(username.bottom,margin=16.dp)
            start.linkTo(parent.start,margin=16.dp)
            end.linkTo(parent.end,margin=16.dp)
            width = Dimension.fillToConstraints
        })
        Button(onClick = viewModel::onLoginClick, modifier = Modifier.constrainAs(normalLogin){
            top.linkTo(password.bottom, margin = 16.dp)
            end.linkTo(parent.end,margin=16.dp)
            start.linkTo(parent.start, margin = 16.dp)
            width=Dimension.fillToConstraints
        },
            shape = RoundedCornerShape(32.dp)

        ) {
            Text("Log In",modifier=Modifier.padding(7.5.dp), fontSize = 14.sp)
        }
        RegOrLoginDuo(modifier = Modifier
            .constrainAs(registerRow) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                width = Dimension.fillToConstraints
            }, navController = navController, route = "Register", textString = "Don't have an Account?", buttonString = "Register")
    }
}
