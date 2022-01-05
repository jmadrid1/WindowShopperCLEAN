package com.example.windowshopperclean.features.shop.data.remote

import com.example.windowshopperclean.core.common.Result
import com.example.windowshopperclean.features.shop.data.CartItem
import com.example.windowshopperclean.features.shop.data.Item
import com.example.windowshopperclean.features.shop.domain.repository.FirebaseShopDAO
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseShopRepo @Inject constructor(
    private var database: DatabaseReference) : FirebaseShopDAO {

    companion object {
        const val KEY_USERS = "users"
        const val KEY_CART = "cart"
    }

    override fun getClothes() = callbackFlow<Result<List<Item>>> {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val queryList = mutableListOf<Item>()
                if(dataSnapshot!!.exists()){
                    for (e in dataSnapshot.children){
                        val item = e.getValue(Item::class.java)
                        if(item != null){
                            queryList.add(item)
                        }
                    }
                    this@callbackFlow.sendBlocking(Result.success(queryList))
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        database.addValueEventListener(postListener)

        awaitClose {
            database.removeEventListener(postListener)
        }
    }

    override fun addToCart(cartItem: CartItem, uid: String) {
        database = Firebase.database.reference
        database.child(KEY_USERS)
            .child(uid)
            .child(KEY_CART)
            .child(cartItem.id.toString())
            .setValue(cartItem)
    }

}