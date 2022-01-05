package com.example.windowshopperclean.features.account.data

import java.io.Serializable

class Account( val username: String,
               var email: String,
               val password: String)
    : Serializable {

    constructor() : this("",
        "",
        ""
    )

}