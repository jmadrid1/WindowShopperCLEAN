package com.example.windowshopperclean.features.cart.domain.repository

import com.example.windowshopperclean.core.common.Result
import com.example.windowshopperclean.features.shop.data.CartItem
import kotlinx.coroutines.flow.Flow

interface FirebaseCartRepoDAO {

    fun getCartItems(uid: String): Flow<Result<List<CartItem>>>

    fun removeItemFromList(uid: String, itemID: String, position: Int)

}