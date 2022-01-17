package com.moneyme.exam.main.views

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.moneyme.exam.R
import com.moneyme.exam.core.base.BaseFragmentView
import com.moneyme.exam.main.adapters.CountriesAdapter
import com.moneyme.exam.main.entities.CountryItem
import kotlinx.android.synthetic.main.view_main.view.*

interface MainViewDelegate {
    fun onRefreshList()
    fun onItemClicked(item: CountryItem)
}

class MainView(context: Context) : BaseFragmentView(context) {

    var delegate: MainViewDelegate? = null
    var isRefreshing: Boolean = false
        set(value) {
            field = value
            swipeRefreshLayout.isRefreshing = value
        }
        get() = swipeRefreshLayout.isRefreshing

    private val adapter = CountriesAdapter { item ->
        delegate?.onItemClicked(item)
    }

    init {
        inflate(context, R.layout.view_main, this)
        with(countriesList) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainView.adapter
        }
        swipeRefreshLayout.setOnRefreshListener {
            delegate?.onRefreshList()
        }
    }

    fun setItems(items: List<CountryItem>) {
        adapter.setItems(items)
    }
}