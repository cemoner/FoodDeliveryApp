package com.example.fooddeliveryapp.authentication.login.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun RegOrLoginDuo(navController: NavController,route:String,textString:String,buttonString:String){
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Text(textString, fontSize = 14.sp, textAlign = TextAlign.Center,)
        Button(
            onClick ={
                navController.navigate(route)
                     },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Transparent
            ),
            elevation = null,
        ) {
            Text(
                text = buttonString,
                color = Color.hsl(0.1f,0.1f,0.5f),
                textDecoration = TextDecoration.Underline,
                fontSize = 14.sp
            )
        }
    }
}