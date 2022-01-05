package com.example.windowshopperclean.features.reviews.domain.use_cases

import com.example.windowshopperclean.features.reviews.domain.repository.FirebaseReviewsRepo
import javax.inject.Inject

class GetReviews @Inject constructor(private val _firebase: FirebaseReviewsRepo) {

    suspend operator fun invoke(itemId: String) = _firebase.getReviews(itemId)

}