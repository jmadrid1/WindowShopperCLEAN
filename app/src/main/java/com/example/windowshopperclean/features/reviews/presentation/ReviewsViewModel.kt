package com.example.windowshopperclean.features.reviews.presentation

import com.example.windowshopperclean.core.common.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.windowshopperclean.core.common.Status
import com.example.windowshopperclean.features.reviews.data.Review
import com.example.windowshopperclean.features.reviews.domain.use_cases.GetReviews
import com.example.windowshopperclean.features.reviews.domain.use_cases.SubmitReview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(
    private val getReviews: GetReviews,
    private val submitReview: SubmitReview): ViewModel() {

    private val _reviewsList = MutableLiveData<Result<List<Review>>>()
    val reviewsList: LiveData<Result<List<Review>>> = _reviewsList

    private val _reviewMessage = MutableStateFlow("")
    private val _reviewCharacterCount = MutableStateFlow(0)

    val isSubmitEnabled: Flow<Boolean> = combine(_reviewMessage) {
        return@combine _reviewMessage.value.isNotEmpty()
    }

    val characterCount: Flow<Int> = combine(_reviewMessage) {
        return@combine _reviewMessage.value.length
    }

    fun setReview(review: String) {
        _reviewMessage.value = review
        _reviewCharacterCount.value = review.length
    }

    @ExperimentalCoroutinesApi
    suspend fun submitReview(itemID: String, review: Review){
        viewModelScope.launch {
           submitReview.invoke(itemID, review)
        }
    }

    @ExperimentalCoroutinesApi
    suspend fun getReviews(itemID: String){
        getReviews.invoke(itemID).collect {
            _reviewsList.postValue(Result.loading(null))
            when(it.status) {
                Status.SUCCESS -> {
                    it.data?.let { reviews ->
                        _reviewsList.postValue(Result.success(reviews))
                    }
                }
                Status.ERROR -> { _reviewsList.postValue(Result.error("Failed to get reviews from Firebase", emptyList()))}
            }
        }
    }

}