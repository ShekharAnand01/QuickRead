package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.Util.Constants
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.models.Article
import com.example.newsapp.screens.HeadlinesFragmentDirections

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article?) -> Unit)? = null

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = differ.currentList[position]
        Glide.with(holder.binding.articleImage.context)
            .load(currentNews.urlToImage ?: R.drawable.img)
            .into(holder.binding.articleImage)
        holder.binding.articleTitle.text = currentNews.title
        holder.binding.articleSource.text = currentNews.source?.name
        holder.binding.articleDescription.text = currentNews.description ?: "Description..."
        holder.binding.articleDateTime.text =
            Constants.convertDateTime(currentNews.publishedAt ?: " ")

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(currentNews!!)
            }
        }
    }

    fun setOnItemClickListener(listener: (Article?) -> Unit) {
        onItemClickListener = listener
    }
}