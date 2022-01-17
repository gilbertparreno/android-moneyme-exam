package com.moneyme.exam.main.fragments

import android.content.Context
import android.os.Bundle
import com.moneyme.exam.MoneyMeApplication
import com.moneyme.exam.R
import com.moneyme.exam.core.base.BaseFragmentLifeCycle
import com.moneyme.exam.core.events.CountriesSynchronizationEvent
import com.moneyme.exam.core.extensions.addFragment
import com.moneyme.exam.core.extensions.showErrorSnackbar
import com.moneyme.exam.core.networking.taskStatus.TaskStatus
import com.moneyme.exam.details.fragments.DetailsFragment
import com.moneyme.exam.main.entities.CountryItem
import com.moneyme.exam.main.viewModels.MainViewModel
import com.moneyme.exam.main.views.MainView
import com.moneyme.exam.main.views.MainViewDelegate
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainFragment : BaseFragmentLifeCycle<MainViewModel, MainView>(), MainViewDelegate {

    override fun inject() {
        MoneyMeApplication.appComponent.inject(this)
    }

    override fun onCreateView(
        context: Context,
        savedInstanceState: Bundle?
    ) = MainView(context).also {
        it.isRefreshing = true
        it.delegate = this
    }

    override fun observerChanges() {
        viewModel.countriesEvent.observe(this) {
            when (it) {
                is TaskStatus.SuccessWithResult -> {
                    contentView.setItems(it.result)
                    contentView.isRefreshing = false
                }
                is TaskStatus.Failure -> {
                    contentView.isRefreshing = false
                    contentView.showErrorSnackbar(
                        it.error.message ?: getString(R.string.generic_error)
                    )
                }
            }
        }
    }

    override fun onViewCreated(
        contentView: MainView,
        savedInstanceState: Bundle?
    ) {
    }

    // MainViewDelegate

    override fun onRefreshList() {
        viewModel.getCountries(false)
    }

    override fun onItemClicked(item: CountryItem) {
        childFragmentManager.beginTransaction().addFragment(
            containerId = R.id.mainFragmentContainer,
            fragmentClass = DetailsFragment::class.java,
            bundle = Bundle().also { it.putInt(DetailsFragment.KEY_COUNTRY_ID, item.id) },
            addToBackStack = true
        ).commit()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: CountriesSynchronizationEvent) {
        eventBus.removeStickyEvent(event)
        viewModel.getCountries()
    }
}