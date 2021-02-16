package com.koshake1.gameofthronesapp.mvp.model.repo.retrofit

import com.koshake1.gameofthronesapp.mvp.model.api.IDataSource
import com.koshake1.gameofthronesapp.mvp.model.cache.room.RoomHousesCache
import com.koshake1.gameofthronesapp.mvp.model.entity.HousesData
import com.koshake1.gameofthronesapp.mvp.model.network.INetworkStatus
import com.koshake1.gameofthronesapp.mvp.model.repo.IHousesRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitHousesRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val roomHousesCache: RoomHousesCache,
) : IHousesRepo {
    override fun getHouses(): Single<List<HousesData>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getHouses().flatMap { houses ->
                    roomHousesCache.putHouses(houses).toSingleDefault(houses)
                }
            } else {
                roomHousesCache.getHouses()
            }
        }.subscribeOn(Schedulers.io())
}