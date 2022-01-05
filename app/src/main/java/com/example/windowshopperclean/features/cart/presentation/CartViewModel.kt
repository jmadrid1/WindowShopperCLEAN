package com.example.windowshopperclean.features.cart.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.windowshopperclean.core.common.Result
import com.example.windowshopperclean.core.common.Status
import com.example.windowshopperclean.features.cart.domain.use_cases.GetCartItems
import com.example.windowshopperclean.features.cart.domain.use_cases.RemoveItemFromCart
import com.example.windowshopperclean.features.shop.data.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItems: GetCartItems,
    private val removeItemFromCart: RemoveItemFromCart): ViewModel() {

    private val _cartList = MutableLiveData<Result<List<CartItem>>>()
    val cartList: LiveData<Result<List<CartItem>>> = _cartList

    val numOfCartItems = MutableLiveData<Int>()
    val totalAmountOfCart = MutableLiveData<Double>()

    @ExperimentalCoroutinesApi
    suspend fun getCartItems(uid: String){
        getCartItems.invoke(uid).collect {
            val queryList = mutableListOf<CartItem>()
            _cartList.postValue(Result.loading(null))
            when(it.status) {
                Status.SUCCESS -> {
                    it.data?.let { items ->
                        var totalPrice = 0.00
                        var amount = 0
                        for(e in items){
                            totalPrice += e!!.price * e.quantity
                            amount += e.quantity
                            queryList.add(e)
                        }
                        _cartList.postValue(Result.success(queryList))
                        numOfCartItems.postValue(amount)
                        totalAmountOfCart.postValue(String.format("%.2f", totalPrice).toDouble())
                    }
                }
                Status.ERROR -> {  _cartList.postValue(Result.error("Failed to grab items from Firebase", emptyList())) }
            }
        }
    }

    suspend fun removeItemFromList(uid: String, itemID: String, position: Int){
        viewModelScope.launch {
           removeItemFromCart(uid, itemID, position)
        }
    }

}