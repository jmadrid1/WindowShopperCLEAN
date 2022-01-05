package com.example.windowshopperclean.features.shop.data

import java.io.Serializable

class CartItem(val id: Int,
               val title: String,
               var size: String,
               var price: Double,
               val thumbnail: String,
               var quantity: Int) : Serializable {

    constructor() : this(0,
        "",
        "",
        0.0,
        "",
        0
    )

}