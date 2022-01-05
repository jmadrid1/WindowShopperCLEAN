package com.example.windowshopperclean.features.shop.domain.use_cases

import com.example.windowshopperclean.features.shop.data.remote.FirebaseShopRepo
import javax.inject.Inject

class GetClothes @Inject constructor(
    private val _firebase: FirebaseShopRepo) {

    suspend operator fun invoke() = _firebase.getClothes()

}