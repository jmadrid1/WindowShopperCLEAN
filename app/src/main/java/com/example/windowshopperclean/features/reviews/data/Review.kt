package com.example.windowshopperclean.features.reviews.data

import java.io.Serializable

class Review(val id: Int,
             val comment: String,
             val date: String,
             val rating: Int) : Serializable
{
    constructor() : this(0,
        "",
        "",
        4,
    )
}