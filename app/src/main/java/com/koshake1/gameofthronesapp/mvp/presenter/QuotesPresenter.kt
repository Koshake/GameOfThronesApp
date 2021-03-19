package com.koshake1.gameofthronesapp.mvp.presenter

import com.koshake1.gameofthronesapp.mvp.model.entity.PersonData
import com.koshake1.gameofthronesapp.mvp.model.repo.IQuotesRepo
import com.koshake1.gameofthronesapp.mvp.model.repo.retrofit.RetrofitQuotesRepo
import com.koshake1.gameofthronesapp.mvp.presenter.list.IQuotesListPresenter
import com.koshake1.gameofthronesapp.mvp.view.IQuotesView
import com.koshake1.gameofthronesapp.mvp.view.list.QuotesItemView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class QuotesPresenter(
    private val person: PersonData
) : MvpPresenter<IQuotesView>() {

    @Inject
    lateinit var quotesRepo: IQuotesRepo
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var mainThreadScheduler: Scheduler

    class QuotesListPresenter : IQuotesListPresenter {
        val quotes = mutableListOf<String>()

        override var itemClickListener: ((QuotesItemView) -> Unit)? = null

        override fun bindView(view: QuotesItemView) {
            val quote = quotes[view.pos]
            quote?.let { view.setQuote(it) }
        }

        override fun getCount(): Int {
            return quotes.size
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    val quotesListPresenter = QuotesListPresenter()

    private fun loadData() {
        quotesRepo.getQuotes(person)
            .observeOn(mainThreadScheduler)
            .subscribe({ quotes ->
                quotesListPresenter.quotes.clear()
                if (quotes.isNotEmpty())
                    quotesListPresenter.quotes.addAll(quotes[0].quotes)
                viewState.updateList()
                println("Quotes loaded! Success!")
            }, {
                println("Quotes not loaded! Error: ${it.message}")
            })
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