package com.example.windowshopperclean.features.reviews.domain.use_cases

import com.example.windowshopperclean.features.reviews.data.Review
import com.example.windowshopperclean.features.reviews.domain.repository.FirebaseReviewsRepo
import javax.inject.Inject

class SubmitReview @Inject constructor(private val _firebase: FirebaseReviewsRepo) {

    suspend operator fun invoke(itemID: String, review: Review) = _firebase.submitReview(itemID, review)

}