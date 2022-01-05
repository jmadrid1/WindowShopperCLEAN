package com.example.windowshopperclean.di

import com.example.windowshopperclean.features.account.data.remote.FirebaseAccountRepo
import com.example.windowshopperclean.features.account.domain.use_cases.AddUserAccount
import com.example.windowshopperclean.features.account.domain.use_cases.GetAccountData
import com.example.windowshopperclean.features.account.domain.use_cases.UpdateEmailAddress
import com.example.windowshopperclean.features.cart.data.remote.FirebaseCartRepo
import com.example.windowshopperclean.features.cart.domain.use_cases.GetCartItems
import com.example.windowshopperclean.features.cart.domain.use_cases.RemoveItemFromCart
import com.example.windowshopperclean.features.reviews.domain.repository.FirebaseReviewsRepo
import com.example.windowshopperclean.features.reviews.domain.use_cases.GetReviews
import com.example.windowshopperclean.features.reviews.domain.use_cases.SubmitReview
import com.example.windowshopperclean.features.shop.data.remote.FirebaseShopRepo
import com.example.windowshopperclean.features.shop.domain.use_cases.GetClothes
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseReference(): DatabaseReference {
        return Firebase.database.getReference("inventory")
    }

    @Provides
    @Singleton
    fun provideAddUserUseCase(repository: FirebaseAccountRepo): AddUserAccount {
        return AddUserAccount(repository)
    }

    @Provides
    @Singleton
    fun provideGetAccountDataUseCase(repository: FirebaseAccountRepo): GetAccountData {
        return GetAccountData(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateEmailAddressUseCase(repository: FirebaseAccountRepo): UpdateEmailAddress {
        return UpdateEmailAddress(repository)
    }

    @Provides
    @Singleton
    fun provideGetClothesUseCase(repository: FirebaseShopRepo): GetClothes {
        return GetClothes(repository)
    }

    @Provides
    @Singleton
    fun provideGetReviewsUseCase(repository: FirebaseReviewsRepo): GetReviews {
        return GetReviews(repository)
    }

    @Provides
    @Singleton
    fun provideSubmitReviewUseCase(repository: FirebaseReviewsRepo): SubmitReview {
        return SubmitReview(repository)
    }

    @Provides
    @Singleton
    fun provideGetCartItemsUseCase(repository: FirebaseCartRepo): GetCartItems {
        return GetCartItems(repository)
    }

    @Provides
    @Singleton
    fun provideRemoveItemFromCartUseCase(repository: FirebaseCartRepo): RemoveItemFromCart {
        return RemoveItemFromCart(repository)
    }

}