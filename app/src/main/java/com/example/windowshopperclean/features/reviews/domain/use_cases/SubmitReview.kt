package com.example.windowshopperclean.features.reviews.domain.use_cases

import com.example.windowshopperclean.features.reviews.data.Review
import com.example.windowshopperclean.features.reviews.domain.repository.FirebaseReviewsRepoImpl
import javax.inject.Inject

class SubmitReview @Inject constructor(private val _firebaseImpl: FirebaseReviewsRepoImpl) {

    suspend operator fun invoke(itemID: String, review: Review) = _firebaseImpl.submitReview(itemID, review)

}