package com.koshake1.gameofthronesapp.mvp.presenter

import com.koshake1.gameofthronesapp.mvp.view.IMainView
import com.koshake1.gameofthronesapp.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(val router: Router) : MvpPresenter<IMainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.HousesScreen())
    }

    fun backClicked() {
        router.exit()
    }
}