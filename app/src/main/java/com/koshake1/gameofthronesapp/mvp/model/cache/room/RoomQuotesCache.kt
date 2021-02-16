package com.koshake1.gameofthronesapp.mvp.model.cache.room

import com.koshake1.gameofthronesapp.mvp.model.cache.IQuotesCache
import com.koshake1.gameofthronesapp.mvp.model.entity.HousesData
import com.koshake1.gameofthronesapp.mvp.model.entity.PersonData
import com.koshake1.gameofthronesapp.mvp.model.entity.QuotesData
import com.koshake1.gameofthronesapp.mvp.model.room.Database
import com.koshake1.gameofthronesapp.mvp.model.room.entity.RoomHousesData
import com.koshake1.gameofthronesapp.mvp.model.room.entity.RoomQuotesData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomQuotesCache(val db: Database) : IQuotesCache {

    override fun getQuotes(person : PersonData) = Single.fromCallable {
        return@fromCallable db.quotesDao.findForPerson(person.name)
            .map { QuotesData(it.name, it.slug, it.quotes) }

    }.subscribeOn(Schedulers.io())


    override fun putQuotes(person : PersonData, quotesData: List<QuotesData>) =
    Completable.fromCallable {
        //val quotesByPerson = db.quotesDao.findForPerson(person.name)
        val roomQuotes = quotesData.map {
            RoomQuotesData(it.name, it.slug, it.quotes, person.name)
        }
        db.quotesDao.insert(roomQuotes)
    }.subscribeOn(Schedulers.io())
}