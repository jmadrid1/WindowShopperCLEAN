package com.example.windowshopperclean.features.cart.domain.use_cases

import com.example.windowshopperclean.features.cart.data.remote.FirebaseCartRepo
import javax.inject.Inject

class RemoveItemFromCart @Inject constructor(
    private val _firebase: FirebaseCartRepo){

    suspend operator fun invoke(uid: String, itemID: String, position: Int) = _firebase.removeItemFromList(uid, itemID, position)

}