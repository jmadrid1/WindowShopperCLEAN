package com.example.windowshopperclean.features.reviews.data.remote

import com.example.windowshopperclean.core.common.Result
import com.example.windowshopperclean.features.reviews.data.Review
import kotlinx.coroutines.flow.Flow

interface FirebaseReviewsDAO {

    fun getReviews(itemId: String): Flow<Result<List<Review>>>

    fun submitReview(itemID: String, review: Review)

}