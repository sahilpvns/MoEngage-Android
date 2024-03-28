package com.moengage.moengage.features.network

import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.moengage.moengage.features.network.modelParser.DashboardParser
import com.moengage.moengage.features.news.model.NewsListResponse

object MEJsonRequest {
    fun homeListRequest(callBack: ApiCallBack<NewsListResponse?>): JsonObjectRequest {
        return JsonObjectRequest(Request.Method.GET,
            UrlConstants.BASE_URL.plus(UrlConstants.NEWS_LIST),
            null, {
                callBack.onSuccess(DashboardParser.newsList(it))
            },
            {
                callBack.onFailure(it.message ?: "Something went wrong")

            })
    }

    fun submit(request: JsonObjectRequest) {
        ApiClient().getInstance()?.queue?.add(request)
    }


}