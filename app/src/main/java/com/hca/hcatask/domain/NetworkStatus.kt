package com.hca.hcatask.domain

sealed class NetworkStatus<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkStatus<T>()
    data class Loading(val status: Boolean) : NetworkStatus<Nothing>()
    data class CustomSignal(val message: String) : NetworkStatus<Nothing>()
    data class Error(val error: String) : NetworkStatus<Nothing>()
}