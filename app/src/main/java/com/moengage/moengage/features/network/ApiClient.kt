package com.moengage.moengage.features.network

import com.android.volley.toolbox.Volley
import com.moengage.moengage.features.utils.NewsApp
import kotlin.concurrent.Volatile


class ApiClient() {
    var queue = Volley.newRequestQueue(NewsApp.mContext)

    @Volatile
    private var sInstance: ApiClient? = null // Volatile is necessary

    fun getInstance(): ApiClient? {
        if (sInstance == null) {
            synchronized(ApiClient::class.java) { // synchronized to avoid concurrency problem
                if (sInstance == null) {
                    sInstance = ApiClient()
                }
            }
        }
        return sInstance
    }
}