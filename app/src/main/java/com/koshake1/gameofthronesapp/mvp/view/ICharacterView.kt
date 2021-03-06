package com.koshake1.gameofthronesapp.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ICharacterView : MvpView {
    fun init()
    fun updateList()
    fun release()
}