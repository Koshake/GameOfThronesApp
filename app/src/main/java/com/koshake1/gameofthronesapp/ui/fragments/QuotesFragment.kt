package com.koshake1.gameofthronesapp.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.android.synthetic.main.appbar.*
import kotlinx.android.synthetic.main.fragment_characters.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_character, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_back -> presenter.backPressed()
        }
        return super.onOptionsItemSelected(item)
    }

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