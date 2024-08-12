package com.example.newsapp.repository

import androidx.room.Query
import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.models.Article

class NewsRepo(private val db: ArticleDatabase) {

    suspend fun getHeadlines(countryCode: String, page: Int) =
        RetrofitInstance.api.getHeadlines(countryCode, page)

    suspend fun searchNews(searchQuery: String, page: Int) =
        RetrofitInstance.api.searchNews(searchQuery, page)

    suspend fun getCategoryNews(countryCode: String, pageNumber: Int, category: String) =
        RetrofitInstance.api.getCategory(countryCode,pageNumber,category)

    suspend fun insert(article: Article) = db.articleDao().insert(article)

    suspend fun delete(article: Article) = db.articleDao().delete(article)

    fun getAllArticles() = db.articleDao().getAllArticles()

    suspend fun isArticleInFavorites(article: Article): Boolean {
        return article.url?.let { db.articleDao().getArticleByUrl(it) } != null
    }
}