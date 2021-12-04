package com.koshake1.gameofthronesapp.mvp.model.repo.retrofit

import com.koshake1.gameofthronesapp.mvp.model.api.IDataSource
import com.koshake1.gameofthronesapp.mvp.model.cache.IQuotesCache
import com.koshake1.gameofthronesapp.mvp.model.entity.PersonData
import com.koshake1.gameofthronesapp.mvp.model.entity.QuotesData
import com.koshake1.gameofthronesapp.mvp.model.network.INetworkStatus
import com.koshake1.gameofthronesapp.mvp.model.repo.IQuotesRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

class RetrofitQuotesRepo(
    val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val roomQuotesCache: IQuotesCache
) : IQuotesRepo {
    override fun getQuotes(person: PersonData) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getQuotes(person.slug).flatMap { quotes ->
                    roomQuotesCache.putQuotes(person, quotes).toSingleDefault(quotes)
                } ?: Single.error<List<QuotesData>>(RuntimeException("No quotes!"))
                    .subscribeOn(Schedulers.io())
            } else {
                roomQuotesCache.getQuotes(person)
            }
        }
}