package com.moengage.moengage.features.news.model

import com.google.gson.annotations.SerializedName

data class NewsListResponse(
    @SerializedName("status") var status: String? = null,

    @SerializedName("articles") var articles: ArrayList<NewsInfo>? = null
)

data class NewsInfo(
    @SerializedName("author") var author: String? = null,

    @SerializedName("title") var title: String? = null,

    @SerializedName("description") var description: String? = null,

    @SerializedName("url") var url: String? = null,

    @SerializedName("urlToImage") var urlToImage: String? = null,

    @SerializedName("publishedAt") var publishedAt: String? = null,

    @SerializedName("content") var content: String? = null
)

