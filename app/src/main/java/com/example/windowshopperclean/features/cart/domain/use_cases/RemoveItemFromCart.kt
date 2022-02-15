package com.example.windowshopperclean.features.cart.domain.use_cases

import com.example.windowshopperclean.features.cart.data.remote.FirebaseCartRepoImpl
import javax.inject.Inject

class RemoveItemFromCart @Inject constructor(
    private val _firebaseImpl: FirebaseCartRepoImpl){

    suspend operator fun invoke(uid: String, itemID: String, position: Int) = _firebaseImpl.removeItemFromList(uid, itemID, position)

}