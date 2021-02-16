package com.koshake1.gameofthronesapp.mvp.model.repo

import com.koshake1.gameofthronesapp.mvp.model.entity.HousesData
import io.reactivex.rxjava3.core.Single

interface IHousesRepo {
    fun getHouses(): Single<List<HousesData>>
}