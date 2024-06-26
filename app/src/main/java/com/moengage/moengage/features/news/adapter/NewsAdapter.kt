package com.moengage.moengage.features.news.adapter

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moengage.moengage.R
import com.moengage.moengage.databinding.NewDataBinding
import com.moengage.moengage.features.news.model.NewsInfo
import com.moengage.moengage.features.news.ui.NewsActivity


class NewsAdapter(var newsData: ArrayList<NewsInfo>?) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private lateinit var binding: NewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = NewDataBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(newsData?.get(holder.adapterPosition))

        holder.itemBinding.whatsappShare.setOnClickListener {
            shareWhatsapp(it.context,  newsData?.get(holder.adapterPosition)?.url)
        }
        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, NewsActivity::class.java)
            intent.putExtra("url", newsData?.get(holder.adapterPosition)?.url)
            it.context.startActivity(intent)
        }
    }

    private fun shareWhatsapp(context: Context, url: String?) {
        val whatsappIntent = Intent(Intent.ACTION_SEND)
        whatsappIntent.setType("text/plain")
        whatsappIntent.setPackage("com.whatsapp")
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "MoEngage News: $url")
        try {
            context.startActivity(whatsappIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(context, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return newsData?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(articles: ArrayList<NewsInfo>?) {
        newsData = articles
        notifyDataSetChanged()
    }

    inner class ViewHolder(var itemBinding: NewDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindItem(dataItem: NewsInfo?) {
            itemBinding.apply {
                tvTitle.text = dataItem?.title
                tvContent.text = dataItem?.content
                tvPublishedAt.text = dataItem?.publishedAt
                tvAuthor.text = dataItem?.author

                Glide.with(root.context).load(dataItem?.urlToImage).error(R.drawable.moengage_image).into(binding.ivImage);

            }
        }

    }
}