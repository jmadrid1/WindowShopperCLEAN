package com.example.windowshopperclean.features.reviews.domain.use_cases

import com.example.windowshopperclean.features.reviews.domain.repository.FirebaseReviewsRepoImpl
import javax.inject.Inject

class GetReviews @Inject constructor(private val _firebaseImpl: FirebaseReviewsRepoImpl) {

    suspend operator fun invoke(itemId: String) = _firebaseImpl.getReviews(itemId)

}