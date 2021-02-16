package com.koshake1.gameofthronesapp.mvp.presenter

import com.koshake1.gameofthronesapp.mvp.model.entity.HousesData
import com.koshake1.gameofthronesapp.mvp.model.entity.PersonData
import com.koshake1.gameofthronesapp.mvp.presenter.list.ICharacterListPresenter
import com.koshake1.gameofthronesapp.mvp.view.ICharacterView
import com.koshake1.gameofthronesapp.mvp.view.list.CharacterItemView
import com.koshake1.gameofthronesapp.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class CharactersPresenter(
    private val house: HousesData,
    val router: Router
) :
    MvpPresenter<ICharacterView>() {

    class CharactersListPresenter : ICharacterListPresenter {
        val characters = mutableListOf<PersonData>()

        override var itemClickListener: ((CharacterItemView) -> Unit)? = null

        override fun bindView(view: CharacterItemView) {
            val person = characters[view.pos]
            person.name?.let { view.setName(it) }
            person.slug?.let { view.setSlug(it) }
        }

        override fun getCount(): Int {
            return characters.size
        }
    }

    val charactersListPresenter = CharactersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        charactersListPresenter.itemClickListener = { itemView ->
            val character = charactersListPresenter.characters[itemView.pos]
            router.navigateTo(Screens.QuotesScreen(character))
        }
    }

    private fun loadData() {
        charactersListPresenter.characters.clear()
        charactersListPresenter.characters.addAll(house.members)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }
}