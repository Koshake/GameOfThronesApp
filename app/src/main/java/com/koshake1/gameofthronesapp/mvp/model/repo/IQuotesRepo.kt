package com.koshake1.gameofthronesapp.mvp.model.repo

import com.koshake1.gameofthronesapp.mvp.model.entity.PersonData
import com.koshake1.gameofthronesapp.mvp.model.entity.QuotesData
import io.reactivex.rxjava3.core.Single

interface IQuotesRepo {
    fun getQuotes(personData: PersonData) : Single<List<QuotesData>>
}