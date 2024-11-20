package com.example.fooddeliveryapp.features.favorites.presentation.contract

import com.example.fooddeliveryapp.features.favorites.domain.model.Product

sealed interface FavoritesPageContract {
    data class UiState(val products:List<Product>)

    sealed interface UiAction{
        object OnBackCLicked: UiAction
        data class OnDeleteClicked(val productId:Int): UiAction
        data class OnAddToCartClicked(val productId:Int): UiAction
        data class OnProductClicked(val productId:Int): UiAction
    }

    sealed interface SideEffect {
        data class ShowToast(val message:String): SideEffect

    }
}