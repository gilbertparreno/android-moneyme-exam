package com.moneyme.exam.details.views

import android.content.Context
import coil.load
import com.moneyme.exam.R
import com.moneyme.exam.core.base.BaseFragmentView
import com.moneyme.exam.core.room.entities.Country
import kotlinx.android.synthetic.main.view_app_bar.view.*
import kotlinx.android.synthetic.main.view_details.view.*

interface DetailsViewDelegate {
    fun onViewBackPressed()
}

class DetailsView(context: Context) : BaseFragmentView(context) {

    var delegate: DetailsViewDelegate? = null

    init {
        inflate(context, R.layout.view_details, this)
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back)
        toolbar.setNavigationOnClickListener {
            delegate?.onViewBackPressed()
        }
    }

    fun setUpView(country: Country) {
        flag.load(country.flag)
        coatOfArm.load(country.coatOfArm)
        name.text = country.name
        region.text =  if (country.subregion != null) {
            country.region.plus(", ").plus(country.subregion)
        } else {
            country.region
        }
    }
}