package com.example.electronicsstoreapp.features.home.domain.usecase

import com.example.electronicsstoreapp.common.domain.model.Product
import com.example.electronicsstoreapp.features.home.domain.repository.ProductDataRepository
import javax.inject.Inject

class GetProductDetailUseCase
    @Inject
    constructor(
        private val productDataRepository: ProductDataRepository,
    ) {
        suspend operator fun invoke(
            store: String,
            productId: Int,
        ): Result<Product> = productDataRepository.getProductDetail(store, productId)
    }
