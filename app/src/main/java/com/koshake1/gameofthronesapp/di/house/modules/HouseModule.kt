package com.koshake1.gameofthronesapp.di.house.modules

import com.koshake1.gameofthronesapp.di.house.HouseScope
import com.koshake1.gameofthronesapp.mvp.model.api.IDataSource
import com.koshake1.gameofthronesapp.mvp.model.cache.IHousesCache
import com.koshake1.gameofthronesapp.mvp.model.cache.room.RoomHousesCache
import com.koshake1.gameofthronesapp.mvp.model.network.INetworkStatus
import com.koshake1.gameofthronesapp.mvp.model.repo.IHousesRepo
import com.koshake1.gameofthronesapp.mvp.model.repo.retrofit.RetrofitHousesRepo
import com.koshake1.gameofthronesapp.mvp.model.room.Database
import dagger.Module
import dagger.Provides

@Module
class HouseModule {

    @HouseScope
    @Provides
    fun housesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IHousesCache) : IHousesRepo =
        RetrofitHousesRepo(api, networkStatus, cache)

    @Provides
    fun roomHousesCache(database: Database): IHousesCache {
        return RoomHousesCache(database)
    }
}