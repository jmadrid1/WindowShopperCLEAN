package com.example.windowshopperclean.features.account.domain.use_cases

import com.example.windowshopperclean.features.account.data.Account
import com.example.windowshopperclean.features.account.data.remote.FirebaseAccountRepo
import javax.inject.Inject

class AddUserAccount @Inject constructor(
    private val _firebase: FirebaseAccountRepo){

    suspend operator fun invoke(account: Account, uid: String){
        _firebase.addUserAccount(account, uid)
    }

}