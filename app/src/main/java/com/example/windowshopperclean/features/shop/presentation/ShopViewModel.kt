package com.example.windowshopperclean.features.shop.presentation

import com.example.windowshopperclean.core.common.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.windowshopperclean.core.common.Status
import com.example.windowshopperclean.features.shop.data.CartItem
import com.example.windowshopperclean.features.shop.data.Item
import com.example.windowshopperclean.features.shop.domain.use_cases.AddToCart
import com.example.windowshopperclean.features.shop.domain.use_cases.GetClothes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val getClothes: GetClothes,
    private val addToCart: AddToCart): ViewModel() {

    private val _clothesList = MutableLiveData<Result<List<Item>>>()
    val clothesList: LiveData<Result<List<Item>>> = _clothesList

    suspend fun getClothes(){
        getClothes.invoke().collect {
            _clothesList.postValue(Result.loading(null))
            when(it.status) {
                Status.SUCCESS -> {
                    it.data?.let { items ->
                        _clothesList.postValue(Result.success(items))
                    }
                }
                Status.ERROR -> { _clothesList.postValue(Result.error("Failed to grab items from Firebase", emptyList())) }
            }
        }
    }

    fun addItemToCart(cartItem: CartItem, uid: String) {
        viewModelScope.launch {
            addToCart(cartItem, uid)
        }
    }

}