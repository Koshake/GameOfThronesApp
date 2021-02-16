package com.koshake1.gameofthronesapp.mvp.model.cache

import com.koshake1.gameofthronesapp.mvp.model.entity.HousesData
import com.koshake1.gameofthronesapp.mvp.model.entity.PersonData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IHousesCache {
    fun getHouses(): Single<List<HousesData>>
    fun putHouses(housesData: List<HousesData>) : Completable
}