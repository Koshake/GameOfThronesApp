package com.koshake1.gameofthronesapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.koshake1.gameofthronesapp.ApiHolder
import com.koshake1.gameofthronesapp.App
import com.koshake1.gameofthronesapp.R
import com.koshake1.gameofthronesapp.di.house.HouseSubComponent
import com.koshake1.gameofthronesapp.mvp.model.cache.room.RoomHousesCache
import com.koshake1.gameofthronesapp.mvp.model.repo.retrofit.RetrofitHousesRepo
import com.koshake1.gameofthronesapp.mvp.model.room.Database
import com.koshake1.gameofthronesapp.mvp.presenter.HousesPresenter
import com.koshake1.gameofthronesapp.mvp.view.IHousesView
import com.koshake1.gameofthronesapp.ui.BackButtonListener
import com.koshake1.gameofthronesapp.ui.fragments.adapter.HousesAdapter
import com.koshake1.gameofthronesapp.ui.image.GlideImageLoader
import com.koshake1.gameofthronesapp.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_houses.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class HousesFragment : MvpAppCompatFragment(), IHousesView, BackButtonListener {

    companion object {
        fun newInstance() = HousesFragment()
    }

    private var houseSubComponent : HouseSubComponent? = null
    val presenter by moxyPresenter {
        houseSubComponent = App.instance.initHouseSubComponent()
        HousesPresenter().apply {
            houseSubComponent?.inject(this)
        }
    }

    var adapter: HousesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_houses, null)


    override fun init() {
        Log.d("tag", "fragment init")
        mainRecycler.layoutManager = LinearLayoutManager(context)
        adapter = HousesAdapter(presenter.housesListPresenter)
        mainRecycler.adapter = adapter
    }

    override fun updateList() {
        Log.d("tag", "fragment update list")
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        houseSubComponent = null
        App.instance.releaseHouseSubComponent()
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}