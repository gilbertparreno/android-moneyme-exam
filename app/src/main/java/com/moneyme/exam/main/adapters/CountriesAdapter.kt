package com.moneyme.exam.main.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.moneyme.exam.R
import com.moneyme.exam.core.base.BaseRecyclerViewAdapter
import com.moneyme.exam.core.extensions.dp
import com.moneyme.exam.core.extensions.inflate
import com.moneyme.exam.core.extensions.setDebounceClickListener
import com.moneyme.exam.main.adapters.callbacks.CountriesCallback
import com.moneyme.exam.main.entities.CountryItem
import kotlinx.android.synthetic.main.item_country.view.*

class CountriesAdapter(
    override val items: MutableList<CountryItem> = mutableListOf(),
    private val onItemClicked: ((CountryItem) -> Unit)? = null
) : BaseRecyclerViewAdapter<CountryItem>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.inflate(
                R.layout.item_country
            )
        )
    }

    override fun onBindViewHolder(view: View, item: CountryItem, position: Int) {
        with(view) {
            flag.load(item.flag)
            countryName.text = item.name
            region.text = item.region
            setDebounceClickListener {
                onItemClicked?.invoke(item)
            }

            if (items.lastOrNull() == item) {
                setPadding(paddingLeft, paddingBottom, paddingRight, dp(16))
            }
        }
    }

    fun setItems(items: List<CountryItem>) {
        val callback = DiffUtil.calculateDiff(CountriesCallback(this.items, items))
        this.items.clear()
        this.items.addAll(items)
        callback.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int) = position
}