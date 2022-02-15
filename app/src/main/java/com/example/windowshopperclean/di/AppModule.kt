package com.example.windowshopperclean.di

import com.example.windowshopperclean.features.account.data.remote.FirebaseAccountRepo
import com.example.windowshopperclean.features.account.domain.use_cases.AddUserAccount
import com.example.windowshopperclean.features.account.domain.use_cases.GetAccountData
import com.example.windowshopperclean.features.account.domain.use_cases.UpdateEmailAddress
import com.example.windowshopperclean.features.cart.data.remote.FirebaseCartRepoImpl
import com.example.windowshopperclean.features.cart.domain.use_cases.GetCartItems
import com.example.windowshopperclean.features.cart.domain.use_cases.RemoveItemFromCart
import com.example.windowshopperclean.features.reviews.domain.repository.FirebaseReviewsRepoImpl
import com.example.windowshopperclean.features.reviews.domain.use_cases.GetReviews
import com.example.windowshopperclean.features.reviews.domain.use_cases.SubmitReview
import com.example.windowshopperclean.features.shop.data.remote.FirebaseShopRepoImpl
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
    fun provideGetClothesUseCase(repository: FirebaseShopRepoImpl): GetClothes {
        return GetClothes(repository)
    }

    @Provides
    @Singleton
    fun provideGetReviewsUseCase(repository: FirebaseReviewsRepoImpl): GetReviews {
        return GetReviews(repository)
    }

    @Provides
    @Singleton
    fun provideSubmitReviewUseCase(repository: FirebaseReviewsRepoImpl): SubmitReview {
        return SubmitReview(repository)
    }

    @Provides
    @Singleton
    fun provideGetCartItemsUseCase(repository: FirebaseCartRepoImpl): GetCartItems {
        return GetCartItems(repository)
    }

    @Provides
    @Singleton
    fun provideRemoveItemFromCartUseCase(repository: FirebaseCartRepoImpl): RemoveItemFromCart {
        return RemoveItemFromCart(repository)
    }

}