package com.moengage.moengage.features.news.ui

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.moengage.moengage.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private var mBinding: ActivityNewsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNewsBinding.inflate(layoutInflater);
        setContentView(mBinding?.root)

        val url = intent.getStringExtra("url")

        mBinding?.apply {
            wvItem.loadUrl(url.toString())
            wvItem.settings.javaScriptEnabled = true
            wvItem.webViewClient = WebViewClient()
        }

    }
}