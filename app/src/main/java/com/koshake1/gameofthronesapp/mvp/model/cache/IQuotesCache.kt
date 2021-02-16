package com.koshake1.gameofthronesapp.mvp.model.cache

import com.koshake1.gameofthronesapp.mvp.model.entity.HousesData
import com.koshake1.gameofthronesapp.mvp.model.entity.PersonData
import com.koshake1.gameofthronesapp.mvp.model.entity.QuotesData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IQuotesCache {
    fun getQuotes(person : PersonData): Single<List<QuotesData>>
    fun putQuotes(person : PersonData, quotesData: List<QuotesData>) : Completable
}