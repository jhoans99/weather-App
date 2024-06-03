package com.svalero.wheatherapp.coomons

sealed class Result<out R> {
    data class Success<out T>(val data:T) : Result<T>()
    class Error<out T>(message: String) : Result<T>()
    object Loading: Result<Nothing>()
}