package com.koshake1.gameofthronesapp.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IHousesView : MvpView{
    fun init()
    fun updateList()
    fun release()
}