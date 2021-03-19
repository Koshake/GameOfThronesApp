package com.koshake1.gameofthronesapp.di.house

import com.koshake1.gameofthronesapp.di.character.CharacterSubComponent
import com.koshake1.gameofthronesapp.di.house.modules.HouseModule
import com.koshake1.gameofthronesapp.di.quote.QuoteSubComponent
import com.koshake1.gameofthronesapp.mvp.presenter.HousesPresenter
import dagger.Subcomponent

@HouseScope
@Subcomponent(
    modules = [
        HouseModule::class
    ]
)
interface HouseSubComponent {
    fun quoteSubComponent() : QuoteSubComponent
    fun characterSubComponent() : CharacterSubComponent
    fun inject(housesPresenter: HousesPresenter)
}