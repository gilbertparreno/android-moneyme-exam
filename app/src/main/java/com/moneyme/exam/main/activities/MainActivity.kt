package com.moneyme.exam.main.activities

import android.os.Bundle
import com.moneyme.exam.R
import com.moneyme.exam.MoneyMeApplication
import com.moneyme.exam.core.base.BaseActivity
import com.moneyme.exam.core.extensions.addFragment
import com.moneyme.exam.core.extensions.getFragmentTag
import com.moneyme.exam.main.fragments.MainFragment

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun inject() {
        MoneyMeApplication.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.apply {
                beginTransaction()
                    .addFragment(
                        containerId = R.id.mainContainer,
                        fragmentClass = MainFragment::class.java,
                        addToBackStack = false
                    ).commit()
            }
            rootFragmentTag = getFragmentTag(MainFragment::class.java)
        }
    }
}