package com.koshake1.gameofthronesapp.di.character

import com.koshake1.gameofthronesapp.di.character.modules.CharacterModule
import com.koshake1.gameofthronesapp.di.house.HouseScope
import com.koshake1.gameofthronesapp.mvp.presenter.CharactersPresenter
import dagger.Subcomponent

@CharacterScope
@Subcomponent(
    modules = [
        CharacterModule::class
    ]
)
interface CharacterSubComponent {
    fun inject(charactersPresenter: CharactersPresenter)
}