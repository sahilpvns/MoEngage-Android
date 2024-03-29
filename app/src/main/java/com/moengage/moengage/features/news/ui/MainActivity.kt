package com.moengage.moengage.features.news.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.moengage.moengage.R
import com.moengage.moengage.databinding.ActivityMainBinding
import com.moengage.moengage.features.network.ApiCallBack
import com.moengage.moengage.features.network.MEJsonRequest
import com.moengage.moengage.features.news.adapter.NewsAdapter
import com.moengage.moengage.features.news.model.NewsInfo
import com.moengage.moengage.features.news.model.NewsListResponse
import com.moengage.moengage.features.utils.UIUtils


class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private var mAdapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(mBinding?.root)


        mAdapter = NewsAdapter(ArrayList())
        mBinding?.rvList?.adapter = mAdapter
        initHomeApi()
        UIUtils.getToke()

        mBinding?.ivSortNews?.setOnClickListener {
            initHomeApi()
            Toast.makeText(this, "News sort: Refresh", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initHomeApi() {
        MEJsonRequest.submit(MEJsonRequest.homeListRequest(object : ApiCallBack<NewsListResponse?> {
            override fun onSuccess(data: NewsListResponse?) {
                if (data?.status.equals("ok", true)) {
                    mAdapter?.updateData(data?.articles)
                    mBinding?.pbLoader?.visibility = View.GONE
                } else {
                    UIUtils.showErrorAlert("Something went wrong")
                }
            }

            override fun onFailure(error: String) {
                UIUtils.showErrorAlert(error)
            }
        }))
    }


    fun shortItem() {
        mAdapter?.updateData(mAdapter?.newsData?.sortedWith(compareByDescending<NewsInfo> { it.publishedAt })?.let { ArrayList(it) })
    }
}