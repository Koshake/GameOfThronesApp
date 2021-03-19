package com.koshake1.gameofthronesapp.di.quote.module

import com.koshake1.gameofthronesapp.di.quote.QuoteScope
import com.koshake1.gameofthronesapp.mvp.model.api.IDataSource
import com.koshake1.gameofthronesapp.mvp.model.cache.IQuotesCache
import com.koshake1.gameofthronesapp.mvp.model.cache.room.RoomQuotesCache
import com.koshake1.gameofthronesapp.mvp.model.network.INetworkStatus
import com.koshake1.gameofthronesapp.mvp.model.repo.IQuotesRepo
import com.koshake1.gameofthronesapp.mvp.model.repo.retrofit.RetrofitQuotesRepo
import com.koshake1.gameofthronesapp.mvp.model.room.Database
import dagger.Module
import dagger.Provides

@Module
class QuoteModule {

    @QuoteScope
    @Provides
    fun quotesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IQuotesCache) : IQuotesRepo =
        RetrofitQuotesRepo(api, networkStatus, cache)

    @Provides
    fun quotesCache(database: Database): IQuotesCache {
        return RoomQuotesCache(database)
    }
}