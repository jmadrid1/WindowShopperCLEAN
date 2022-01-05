package com.example.windowshopperclean.features.account.domain.repository

import com.example.windowshopperclean.core.common.Result
import com.example.windowshopperclean.features.account.data.Account
import kotlinx.coroutines.flow.Flow

interface FirebaseAccountDAO {

    fun getAccountData(uid: String): Flow<Result<Account>>

    fun addUserAccount(account: Account, uid: String)

    fun updateEmailAddress(uid: String, email: String)

}