package com.example.electronicsstoreapp.features.profile.authentication.presentation.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.electronicsstoreapp.features.profile.authentication.presentation.components.EmailTextField
import com.example.electronicsstoreapp.features.profile.authentication.presentation.components.FormTextFieldContent
import com.example.electronicsstoreapp.features.profile.authentication.presentation.components.PasswordTextField
import com.example.electronicsstoreapp.features.profile.authentication.presentation.components.RegOrLoginDuo
import com.example.electronicsstoreapp.features.profile.authentication.presentation.contract.RegisterContract.SideEffect
import com.example.electronicsstoreapp.features.profile.authentication.presentation.contract.RegisterContract.UiAction
import com.example.electronicsstoreapp.features.profile.authentication.presentation.contract.RegisterContract.UiState
import com.example.electronicsstoreapp.features.profile.authentication.presentation.viewmodel.RegisterViewModel
import com.example.electronicsstoreapp.mvi.CollectSideEffect
import com.example.electronicsstoreapp.mvi.unpack
import kotlinx.coroutines.flow.Flow

@Composable
fun RegisterScreen() {
    val viewModel: RegisterViewModel = hiltViewModel()
    val (uiState, onAction, sideEffect) = viewModel.unpack()
    RegisterContent(uiState, onAction, sideEffect)
}

@Composable
fun RegisterContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    sideEffect: Flow<SideEffect>,
) {
    val context = LocalContext.current

    CollectSideEffect(sideEffect) {
        when (it) {
            is SideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }
    Scaffold(
        topBar = { TopBar(onAction) },
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            FormTextFieldContent(uiState.name, { onAction(UiAction.OnNameChange(it)) }, "Name")
            FormTextFieldContent(uiState.surName, { onAction(UiAction.OnSurNameChange(it)) }, "Surname")
            FormTextFieldContent(uiState.phone, { onAction(UiAction.OnPhoneChange(it)) }, "Phone")
            FormTextFieldContent(uiState.address, { onAction(UiAction.OnAddressChange(it)) }, "Address")
            EmailTextField(emailText = uiState.email, { onAction(UiAction.OnEmailChange(it)) })
            PasswordTextField(uiState.password, { onAction(UiAction.OnPasswordChange(it)) }, { onAction(UiAction.OnRegisterButtonClicked) })
            if (uiState.showProgress) {
                CircularProgressIndicator()
            }
            Button(modifier = Modifier.padding(top = 6.dp), onClick = {
                onAction(UiAction.OnRegisterButtonClicked)
            }, shape = RoundedCornerShape(32.dp)) { Text("Register", modifier = Modifier.padding(7.5.dp), fontSize = 14.sp) }
            RegOrLoginDuo({ onAction(UiAction.OnLoginButtonClicked) }, textString = "Already have an Account?", buttonString = "Log In")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onAction: (UiAction) -> Unit) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { onAction(UiAction.OnBackButtonClicked) }) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.surfaceTint)
            }
        },
    )
}
