package com.moengage.moengage.features.network

interface ApiCallBack<T> {
    fun onSuccess(data:T)
    fun onFailure(error:String)
}