package com.koshake1.gameofthronesapp.ui

import android.os.Bundle
import com.koshake1.gameofthronesapp.App
import com.koshake1.gameofthronesapp.R
import com.koshake1.gameofthronesapp.mvp.presenter.MainPresenter
import com.koshake1.gameofthronesapp.mvp.view.IMainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), IMainView {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter().apply { App.instance.appComponent.inject(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}