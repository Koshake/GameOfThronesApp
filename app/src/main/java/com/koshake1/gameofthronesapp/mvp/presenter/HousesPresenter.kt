package com.koshake1.gameofthronesapp.mvp.presenter

import com.koshake1.gameofthronesapp.mvp.model.entity.HousesData
import com.koshake1.gameofthronesapp.mvp.model.repo.retrofit.RetrofitHousesRepo
import com.koshake1.gameofthronesapp.mvp.presenter.list.IHouseListPresenter
import com.koshake1.gameofthronesapp.mvp.view.IHousesView
import com.koshake1.gameofthronesapp.mvp.view.list.HouseItemView
import com.koshake1.gameofthronesapp.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class HousesPresenter(
    val housesRepo: RetrofitHousesRepo,
    val mainThreadScheduler: Scheduler,
    val router: Router
) : MvpPresenter<IHousesView>() {

    class HousesListPresenter : IHouseListPresenter {
        val houses = mutableListOf<HousesData>()

        override var itemClickListener: ((HouseItemView) -> Unit)? = null

        override fun getCount() = houses.size

        override fun bindView(view: HouseItemView) {
            val house = houses[view.pos]

            house.name?.let { view.setName(it) }
        }
    }

    val housesListPresenter = HousesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        housesListPresenter.itemClickListener = { itemView ->
            val house = housesListPresenter.houses[itemView.pos]
            router.navigateTo(Screens.CharacterScreen(house))
        }
    }

    private fun loadData() {
        housesRepo.getHouses()
            .observeOn(mainThreadScheduler)
            .subscribe({ houses ->
                housesListPresenter.houses.clear()
                housesListPresenter.houses.addAll(houses)
                viewState.updateList()
                println("Houses loaded! Success!")
            }, {
                println("Houses not loaded! Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}