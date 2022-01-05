package com.example.windowshopperclean.features.account.data.remote

import com.example.windowshopperclean.core.common.Result
import com.example.windowshopperclean.features.account.data.Account
import com.example.windowshopperclean.features.account.domain.repository.FirebaseAccountDAO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseAccountRepo @Inject constructor(
    private var database: DatabaseReference) : FirebaseAccountDAO {

    private lateinit var auth: FirebaseAuth

    companion object {
        const val KEY_USERS = "users"
        const val KEY_USERNAME = "username"
        const val KEY_EMAIL = "email"
        const val KEY_PASSWORD = "password"
    }

    override fun getAccountData(uid: String) = callbackFlow<Result<Account>> {
        database = Firebase.database
            .getReference(KEY_USERS)
            .child(uid)

        val userListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val name = dataSnapshot.child(KEY_USERNAME).getValue(String::class.java).toString()
                val emailAddress = dataSnapshot.child(KEY_EMAIL).getValue(String::class.java).toString()
                val password = dataSnapshot.child(KEY_PASSWORD).getValue(String::class.java).toString()
                val account = Account(name, emailAddress, password)

                this@callbackFlow.sendBlocking(Result.success(account))
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        database.addValueEventListener(userListener)

        awaitClose {
            database.removeEventListener(userListener)
        }
    }

    override fun addUserAccount(account: Account, uid: String) {
        auth = Firebase.auth

        database = Firebase.database.reference
        database.child(KEY_USERS)
            .child(uid)
            .setValue(account)
    }

    override fun updateEmailAddress(uid: String, email: String) {
        auth = Firebase.auth

        database = Firebase.database.reference
        database.child(KEY_USERNAME)
            .child(uid)
            .child(KEY_EMAIL)
            .setValue(email)

        auth.currentUser.updateEmail(email)
    }

}