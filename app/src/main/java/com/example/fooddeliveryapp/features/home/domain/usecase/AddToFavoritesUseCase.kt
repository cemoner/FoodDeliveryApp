package com.example.fooddeliveryapp.features.home.domain.usecase

import com.example.fooddeliveryapp.features.home.domain.model.AddResult
import com.example.fooddeliveryapp.features.home.domain.repository.ProductActionRepository
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(private val productActionRepository: ProductActionRepository,){
    suspend operator fun invoke(store:String,userId:String,productId:Int): Result<AddResult> = productActionRepository.addToFavorites(store,userId,productId)
}