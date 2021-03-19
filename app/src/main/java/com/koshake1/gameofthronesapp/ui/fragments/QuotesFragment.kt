package com.koshake1.gameofthronesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.koshake1.gameofthronesapp.App
import com.koshake1.gameofthronesapp.R
import com.koshake1.gameofthronesapp.di.quote.QuoteSubComponent
import com.koshake1.gameofthronesapp.mvp.model.entity.PersonData
import com.koshake1.gameofthronesapp.mvp.presenter.QuotesPresenter
import com.koshake1.gameofthronesapp.mvp.view.IQuotesView
import com.koshake1.gameofthronesapp.ui.BackButtonListener
import com.koshake1.gameofthronesapp.ui.fragments.adapter.QuotesAdapter
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

    private var quoteSubComponent: QuoteSubComponent? = null
    val presenter by moxyPresenter {
        val person = arguments?.getParcelable<PersonData>(QUOT_ARG) as PersonData
        quoteSubComponent = App.instance.initQuoteSubComponent()
        QuotesPresenter(person).apply {
            quoteSubComponent?.inject(
                this
            )
        }
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
        quoteSubComponent = null
        App.instance.releaseQuoteSubComponent()
    }

    override fun backPressed() = presenter.backPressed()
}