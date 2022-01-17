package com.moneyme.exam.main.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.moneyme.exam.main.entities.CountryItem

class CountriesCallback(
    private val oldList: List<CountryItem>,
    private val newList: List<CountryItem>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ) = oldList[oldItemPosition].id == newList[oldItemPosition].id

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ) = oldList[oldItemPosition] == newList[oldItemPosition]
}