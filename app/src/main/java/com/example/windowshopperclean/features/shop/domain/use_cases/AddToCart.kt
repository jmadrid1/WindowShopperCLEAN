package com.example.windowshopperclean.features.shop.domain.use_cases

import com.example.windowshopperclean.features.shop.data.CartItem
import com.example.windowshopperclean.features.shop.data.remote.FirebaseShopRepoImpl
import javax.inject.Inject

class AddToCart @Inject constructor(private val _firebaseImpl: FirebaseShopRepoImpl) {

    suspend operator fun invoke(cartItem: CartItem, uid: String) = _firebaseImpl.addToCart(cartItem, uid)

}