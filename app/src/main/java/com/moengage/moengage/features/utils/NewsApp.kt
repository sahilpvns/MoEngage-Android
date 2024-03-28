package com.moengage.moengage.features.utils

import android.app.Application
import android.content.Context

class NewsApp : Application() {
    companion object {
        var mContext: Context? = null
    }

    override fun attachBaseContext(base: Context?) {
        mContext = base
        super.attachBaseContext(base)

    }
}