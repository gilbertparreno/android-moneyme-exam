package com.moneyme.exam.details.fragments

import android.content.Context
import android.os.Bundle
import com.moneyme.exam.MoneyMeApplication
import com.moneyme.exam.R
import com.moneyme.exam.core.base.BaseFragmentLifeCycle
import com.moneyme.exam.core.extensions.showErrorSnackbar
import com.moneyme.exam.core.networking.taskStatus.TaskStatus
import com.moneyme.exam.details.viewModels.DetailsViewModel
import com.moneyme.exam.details.views.DetailsView
import com.moneyme.exam.details.views.DetailsViewDelegate

class DetailsFragment : BaseFragmentLifeCycle<DetailsViewModel, DetailsView>(),
    DetailsViewDelegate {

    companion object {
        const val KEY_COUNTRY_ID = "country_id"
    }

    private val countryId: Int
        get() {
            return arguments?.getInt(KEY_COUNTRY_ID) ?: -1
        }

    override fun inject() {
        MoneyMeApplication.appComponent.inject(this)
    }

    override fun onCreateView(
        context: Context,
        savedInstanceState: Bundle?
    ) = DetailsView(context).also {
        it.delegate = this
    }

    override fun onViewCreated(
        contentView: DetailsView,
        savedInstanceState: Bundle?
    ) {
        viewModel.getCountry(countryId)
    }

    override fun observerChanges() {
        viewModel.countryEvent.observe(this) {
            when (it) {
                is TaskStatus.SuccessWithResult -> {
                    contentView.setUpView(it.result)
                }
                is TaskStatus.Failure -> {
                    contentView.showErrorSnackbar(getString(R.string.generic_error))
                }
            }
        }
    }

    // DetailsViewDelegate

    override fun onViewBackPressed() {
        super.onBackPressed()
    }
}