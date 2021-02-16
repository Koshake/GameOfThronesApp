package com.koshake1.gameofthronesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.koshake1.gameofthronesapp.ApiHolder
import com.koshake1.gameofthronesapp.App
import com.koshake1.gameofthronesapp.R
import com.koshake1.gameofthronesapp.mvp.model.cache.room.RoomQuotesCache
import com.koshake1.gameofthronesapp.mvp.model.entity.PersonData
import com.koshake1.gameofthronesapp.mvp.model.repo.retrofit.RetrofitQuotesRepo
import com.koshake1.gameofthronesapp.mvp.model.room.Database
import com.koshake1.gameofthronesapp.mvp.presenter.QuotesPresenter
import com.koshake1.gameofthronesapp.mvp.view.IQuotesView
import com.koshake1.gameofthronesapp.ui.BackButtonListener
import com.koshake1.gameofthronesapp.ui.fragments.adapter.QuotesAdapter
import com.koshake1.gameofthronesapp.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_quotes.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class QuotesFragment : MvpAppCompatFragment(), IQuotesView, BackButtonListener {
    companion object {
        private const val QUOT_ARG = "quote"
        fun newInstance(personData: PersonData) = QuotesFragment().apply {
            arguments = Bundle().apply {
                putParcelable(QUOT_ARG, personData)
            }
        }
    }

    val presenter by moxyPresenter {
        val person = arguments?.getParcelable<PersonData>(QUOT_ARG) as PersonData
        QuotesPresenter(
            person,
            RetrofitQuotesRepo(
                ApiHolder().api, AndroidNetworkStatus(App.instance), RoomQuotesCache(
                    Database.getInstance()
                )
            ), AndroidSchedulers.mainThread(), App.instance.router
        )
    }

    private var adapter: QuotesAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_quotes, null)

    override fun init() {
        quotesRecycler.layoutManager = LinearLayoutManager(context)
        adapter = QuotesAdapter(presenter.quotesListPresenter)
        quotesRecycler.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {

    }

    override fun backPressed() = presenter.backPressed()
}