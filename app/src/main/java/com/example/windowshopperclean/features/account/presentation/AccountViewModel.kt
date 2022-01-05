package com.example.windowshopperclean.features.account.presentation

import com.example.windowshopperclean.core.common.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.windowshopperclean.core.common.Status
import com.example.windowshopperclean.features.account.domain.use_cases.GetAccountData
import com.example.windowshopperclean.features.account.domain.use_cases.UpdateEmailAddress
import com.example.windowshopperclean.features.account.data.Account
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getAccountData: GetAccountData,
    private val updateEmailAddress: UpdateEmailAddress): ViewModel() {

    private val _username = MutableLiveData<Result<String>>()
    val username: LiveData<Result<String>> = _username

    private val _email = MutableLiveData<Result<String>>()
    val email: LiveData<Result<String>> = _email

    private val _wasEmailUpdated = MutableLiveData<Boolean>()
    val wasEmailUpdated : LiveData<Boolean> = _wasEmailUpdated

    private lateinit var auth: FirebaseAuth

    @ExperimentalCoroutinesApi
    fun getUsersData(uid : String){
        viewModelScope.launch {
            getAccountData.invoke(uid).collect {
                when(it.status) {
                    Status.SUCCESS -> {
                        val account: Account = it.data!!

                        _username.postValue(Result.success(account.username))
                        _email.postValue(Result.success(account.email))
                    }
                }
            }
        }
    }

    fun updateEmailAddress(uid: String, email: String){
        viewModelScope.launch {
            updateEmailAddress.invoke(uid, email)
            _wasEmailUpdated.postValue(true)
        }
    }

    fun signOut(){
        auth = Firebase.auth
        Firebase.auth.signOut()
    }

}