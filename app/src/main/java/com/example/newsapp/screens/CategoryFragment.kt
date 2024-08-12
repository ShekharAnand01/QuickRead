package com.example.newsapp.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.Util.Constants
import com.example.newsapp.Util.Resource
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentCategoryBinding
import com.example.newsapp.databinding.FragmentHeadlinesBinding
import com.example.newsapp.viewmodel.NewsViewModel


class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var retryButton: Button
    private lateinit var errorText: TextView
    private lateinit var itemHeadLinesError: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setUpAdapter()

        itemHeadLinesError = view.findViewById(R.id.itemHeadlinesError)
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.item_error, null)

        retryButton = view.findViewById(R.id.retryButton)
        errorText = view.findViewById(R.id.errorText)

        newsAdapter.setOnItemClickListener { article ->
            try {
                article?.url?.let {
                    val action =
                        CategoryFragmentDirections.actionCategoryFragmentToArticleFragment(article)
                    Log.d("Navigation", "Navigating with action: $action")
                    findNavController().navigate(action)
                } ?: run {
                    Toast.makeText(activity, "Article URL is missing", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(activity, "Article not found", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        viewModel.categorynews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success<*> -> {
                    hideProgressBar()
                    hideErrorMessage()
                    response.data?.let { articleResponse ->
                        newsAdapter.differ.submitList(articleResponse.articles.toList())
                        val totalPages = articleResponse.totalResults / Constants.QUERY_PAGE_SIZE
                        isLastPage = viewModel.categorynewsPage == totalPages + 2
                        if (isLastPage) {
                            binding.recyclerHeadlines.setPadding(0, 0, 0, 0)
                        }
                    }
                }

                is Resource.Error<*> -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "Sorry error: $message", Toast.LENGTH_LONG).show()
                        showErrorMessage(message)
                    }
                }

                is Resource.Loading<*> -> {
                    showProgressBar()
                }
            }
        }

        binding.business.setOnClickListener { fetchCategoryNews("business") }
        binding.entertainment.setOnClickListener { fetchCategoryNews("entertainment") }
        binding.health.setOnClickListener { fetchCategoryNews("health") }
        binding.science.setOnClickListener { fetchCategoryNews("science") }
        binding.sports.setOnClickListener { fetchCategoryNews("sports") }
        binding.technology.setOnClickListener { fetchCategoryNews("technology") }



        binding.itemHeadlinesError.retryButton.setOnClickListener {

            viewModel.getNewsByCategory("general")
        }

    }

    var isError = false
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private fun fetchCategoryNews(category: String) {
        viewModel.categorynewsPage = 1
        viewModel.categoryResponse = null
        viewModel.getNewsByCategory(category)
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideErrorMessage() {
        itemHeadLinesError.visibility = View.GONE
        binding.recyclerHeadlines.visibility = View.VISIBLE
        isError = false

    }

    private fun showErrorMessage(message: String) {
        itemHeadLinesError.visibility = View.VISIBLE
        binding.recyclerHeadlines.visibility = View.INVISIBLE
        errorText.text = message
        isError = true
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNoError = !isError
            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= Constants.QUERY_PAGE_SIZE
            val shouldPaginate =
                isNoError && isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                viewModel.getNewsByCategory("")
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

    private fun setUpAdapter() {
        newsAdapter = NewsAdapter()
        binding.recyclerHeadlines.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@CategoryFragment.scrollListener)

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



