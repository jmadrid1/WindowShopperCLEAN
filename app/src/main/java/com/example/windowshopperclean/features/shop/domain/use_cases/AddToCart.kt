package com.example.windowshopperclean.features.shop.domain.use_cases

import com.example.windowshopperclean.features.shop.data.CartItem
import com.example.windowshopperclean.features.shop.data.remote.FirebaseShopRepo
import javax.inject.Inject

class AddToCart @Inject constructor(private val _firebase: FirebaseShopRepo) {

    suspend operator fun invoke(cartItem: CartItem, uid: String) = _firebase.addToCart(cartItem, uid)

}