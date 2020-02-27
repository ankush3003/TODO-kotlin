package com.nagarro.todoapp.data.repository

sealed class UseCaseResult<T : Any> {
    class Success<T : Any>(val data: T) : UseCaseResult<T>()  //Q: reified
    class Error<T : Any>(val exception: Throwable) : UseCaseResult<T>() //Q: generics - in and out and where
    //Q: How to make singleton
    //Q: What are object
}