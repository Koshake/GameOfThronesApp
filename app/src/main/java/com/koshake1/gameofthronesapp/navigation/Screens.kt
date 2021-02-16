package com.koshake1.gameofthronesapp.navigation

import com.koshake1.gameofthronesapp.mvp.model.entity.HousesData
import com.koshake1.gameofthronesapp.mvp.model.entity.PersonData
import com.koshake1.gameofthronesapp.ui.fragments.CharacterFragment
import com.koshake1.gameofthronesapp.ui.fragments.HousesFragment
import com.koshake1.gameofthronesapp.ui.fragments.QuotesFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class HousesScreen : SupportAppScreen() {
        override fun getFragment() = HousesFragment.newInstance()
    }

    class CharacterScreen(private val house: HousesData) : SupportAppScreen() {
        override fun getFragment() = CharacterFragment.newInstance(house)
    }

    class QuotesScreen(private val person: PersonData) : SupportAppScreen() {
        override fun getFragment() = QuotesFragment.newInstance(person)
    }
}