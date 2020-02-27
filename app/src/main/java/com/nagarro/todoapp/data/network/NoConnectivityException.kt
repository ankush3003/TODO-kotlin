package com.nagarro.todoapp.data.network

import java.io.IOException

class NoConnectivityException : IOException() {
    // You can send any message whatever you want from here.
    //Q: String vs new String object
    override val message: String
            get() = "No Internet Connection"
    }
