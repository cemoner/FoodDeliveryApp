package com.example.fooddeliveryapp.authentication.login.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddeliveryapp.authentication.components.Password
import com.example.fooddeliveryapp.authentication.components.RegOrLoginDuo
import com.example.fooddeliveryapp.authentication.components.RegisterButton
import com.example.fooddeliveryapp.authentication.components.UserName
import com.example.fooddeliveryapp.authentication.login.presentation.viewmodel.RegisterViewModel


@Composable
fun Register(navController: NavController){
    val viewModel: RegisterViewModel = hiltViewModel()
    val userNameText by viewModel.userNameText.collectAsState()
    val passwordText by viewModel.passwordText.collectAsState()
    val nameText by viewModel.name.collectAsState()
    val surNameText by viewModel.surName.collectAsState()

    val topBias = 1f / 3.5f
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (username, password,name,surname,register,loginRow) = createRefs()

        TextField(value = nameText,
            onValueChange = viewModel::onNameChange,
            label = {Text("Name")},
            placeholder = {Text("Enter your name")},
            shape = RoundedCornerShape(32.dp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent, // Removes the underline when focused
                unfocusedIndicatorColor = Color.Transparent, // Removes the underline when not focused
                cursorColor = Color.Black
            ),
            leadingIcon = {
                Icon(
                    Icons.Default.DriveFileRenameOutline,
                    contentDescription = "",
                    tint = Color.Gray
                )}
            ,
            modifier = Modifier.constrainAs(name){
                linkTo(top = parent.top,bottom=parent.bottom,bias = topBias)
                end.linkTo(parent.end,margin=16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                width= Dimension.fillToConstraints
            }
            )
        TextField(value = surNameText,
            onValueChange = viewModel::onSurNameChange,
            label = {Text("Surname")},
            placeholder = {Text("Enter your surname")},
            shape = RoundedCornerShape(32.dp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent, // Removes the underline when focused
                unfocusedIndicatorColor = Color.Transparent, // Removes the underline when not focused
                cursorColor = Color.Black
            ),
            leadingIcon = {
                Icon(
                Icons.Default.DriveFileRenameOutline,
                contentDescription = "",
                tint = Color.Gray
            )}
            ,
            modifier = Modifier.constrainAs(surname){
                top.linkTo(name.bottom, margin = 16.dp)
                end.linkTo(parent.end,margin=16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                width= Dimension.fillToConstraints
            }
        )
        UserName(userNameText = userNameText,
            function = viewModel::onUserNameChange,
            modifier = Modifier.constrainAs(username){
                top.linkTo(surname.bottom,margin=14.dp)
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

        RegisterButton(navController = navController, modifier = Modifier.constrainAs(register){
            top.linkTo(password.bottom, margin = 20.dp)
            end.linkTo(parent.end,margin=16.dp)
            start.linkTo(parent.start, margin = 16.dp)
            width= Dimension.fillToConstraints
        },"Profile")

        RegOrLoginDuo(modifier = Modifier
            .constrainAs(loginRow) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                width = Dimension.fillToConstraints
            }, navController = navController, route = "Login", textString = "Already have an Account?", buttonString = "Log In")
    }

}