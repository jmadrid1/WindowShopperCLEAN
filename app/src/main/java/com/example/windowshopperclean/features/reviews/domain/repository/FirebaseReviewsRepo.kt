package com.example.windowshopperclean.features.reviews.domain.repository

import com.example.windowshopperclean.core.common.Result
import com.example.windowshopperclean.features.reviews.data.remote.FirebaseReviewsDAO
import com.example.windowshopperclean.features.reviews.data.Review
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

class FirebaseReviewsRepo @Inject constructor(
    private var database: DatabaseReference) : FirebaseReviewsDAO {

    companion object {
        const val KEY_INVENTORY = "inventory"
        const val KEY_REVIEWS = "reviews"
    }

    override fun getReviews(itemID: String) = callbackFlow<Result<List<Review>>> {
        database = Firebase.database
            .getReference(KEY_INVENTORY)
            .child(itemID)
            .child(KEY_REVIEWS)

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val queryList = mutableListOf<Review>()
                if(dataSnapshot!!.exists()){
                    for (e in dataSnapshot.children){
                        val item = e.getValue(Review::class.java)
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

    override fun submitReview(itemID: String, review: Review) {
        database = Firebase.database.reference
        database.child(KEY_INVENTORY)
            .child(itemID)
            .child(KEY_REVIEWS)
            .push().setValue(review)
    }

}