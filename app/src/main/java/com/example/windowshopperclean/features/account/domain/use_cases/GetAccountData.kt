package com.example.windowshopperclean.features.account.domain.use_cases

import com.example.windowshopperclean.features.account.data.remote.FirebaseAccountRepo
import javax.inject.Inject

class GetAccountData @Inject constructor(
    private val _firebase: FirebaseAccountRepo) {

    suspend operator fun invoke(uid: String) =_firebase.getAccountData(uid)

}