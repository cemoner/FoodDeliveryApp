package com.example.fooddeliveryapp.authentication.login.presentation.contracts


interface LoginContract {
    data class UiState(val email: String,val password: String,val errorText:String,val showProgress:Boolean)

    sealed interface UiAction {
        object OnLoginClick : UiAction
        data class OnEmailChange(val email: String) : UiAction
        data class OnPasswordChange(val password: String) : UiAction

    }

    sealed interface SideEffect {
        data class ShowToast(val message:String) : SideEffect
        data class Navigate(val route:String) : SideEffect
    }
}