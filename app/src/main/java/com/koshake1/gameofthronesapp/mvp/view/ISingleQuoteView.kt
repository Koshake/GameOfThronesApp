package com.koshake1.gameofthronesapp.mvp.view

import com.google.android.material.navigation.NavigationView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ISingleQuoteView : MvpView {
    fun updateText(text: String)
    fun updateName(text: String)
    fun initDrawer()
}