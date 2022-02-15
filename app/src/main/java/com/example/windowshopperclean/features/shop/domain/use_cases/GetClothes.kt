package com.example.windowshopperclean.features.shop.domain.use_cases

import com.example.windowshopperclean.features.shop.data.remote.FirebaseShopRepoImpl
import javax.inject.Inject

class GetClothes @Inject constructor(
    private val _firebaseImpl: FirebaseShopRepoImpl) {

    suspend operator fun invoke() = _firebaseImpl.getClothes()

}