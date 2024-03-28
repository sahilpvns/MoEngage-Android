package com.moengage.moengage.features.network.modelParser

import com.moengage.moengage.features.news.model.NewsInfo
import com.moengage.moengage.features.news.model.NewsListResponse
import org.json.JSONArray
import org.json.JSONObject

object DashboardParser {

    fun newsList(jsonObject: JSONObject): NewsListResponse? {
        // return Gson().fromJson(jsonObject.toString(), NewsListResponse::class.java)
        return try {
            NewsListResponse(
                status = jsonObject.optString("status"),
                articles = getArticles(jsonObject.optJSONArray("articles"))
            )
        } catch (e: Exception) {
            null
        }
    }

    private fun getArticles(optJSONArray: JSONArray?): ArrayList<NewsInfo>? {
        val list = ArrayList<NewsInfo>()
        for (i in 0 until (optJSONArray?.length() ?: 0)) {
            list.add(
                NewsInfo(
                    author = optJSONArray?.optJSONObject(i)?.optString("author"),
                    title = optJSONArray?.optJSONObject(i)?.optString("title"),
                    description = optJSONArray?.optJSONObject(i)?.optString("description"),
                    url = optJSONArray?.optJSONObject(i)?.optString("url"),
                    urlToImage = optJSONArray?.optJSONObject(i)?.optString("urlToImage"),
                    publishedAt = optJSONArray?.optJSONObject(i)?.optString("publishedAt"),
                    content = optJSONArray?.optJSONObject(i)?.optString("content"),
                )
            )
        }
        return list
    }

}