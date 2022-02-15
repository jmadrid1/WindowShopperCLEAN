package com.example.windowshopperclean.features.shop.domain.repository

import com.example.windowshopperclean.core.common.Result
import com.example.windowshopperclean.features.shop.data.CartItem
import com.example.windowshopperclean.features.shop.data.Item
import kotlinx.coroutines.flow.Flow

interface FirebaseShopRepo {

    fun getClothes(): Flow<Result<List<Item>>>

    fun addToCart(cartItem: CartItem, uid: String)

}