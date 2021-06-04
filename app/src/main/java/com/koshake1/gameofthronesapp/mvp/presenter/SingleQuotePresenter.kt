package com.koshake1.gameofthronesapp.mvp.presenter

import com.koshake1.gameofthronesapp.mvp.view.ISingleQuoteView
import com.koshake1.gameofthronesapp.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SingleQuotePresenter(val name: String, val quote : String) : MvpPresenter<ISingleQuoteView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.updateText(quote)
        viewState.updateName(name)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}