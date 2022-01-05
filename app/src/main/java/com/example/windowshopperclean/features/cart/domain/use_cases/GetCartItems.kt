package com.example.windowshopperclean.features.cart.domain.use_cases

import com.example.windowshopperclean.features.cart.data.remote.FirebaseCartRepo
import javax.inject.Inject

class GetCartItems @Inject constructor(
    private val _firebase: FirebaseCartRepo){

    suspend operator fun invoke(uid :String) = _firebase.getCartItems(uid)

}