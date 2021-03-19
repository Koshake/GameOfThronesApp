package com.koshake1.gameofthronesapp.di.quote

import com.koshake1.gameofthronesapp.di.quote.module.QuoteModule
import com.koshake1.gameofthronesapp.mvp.presenter.QuotesPresenter
import dagger.Subcomponent

@QuoteScope
@Subcomponent(
    modules = [
        QuoteModule::class
    ]
)
interface QuoteSubComponent {
    fun inject(quotesPresenter: QuotesPresenter)
}