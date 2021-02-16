package com.koshake1.gameofthronesapp.mvp.model.cache

import com.koshake1.gameofthronesapp.mvp.model.entity.HousesData
import com.koshake1.gameofthronesapp.mvp.model.entity.PersonData
import com.koshake1.gameofthronesapp.mvp.model.entity.QuotesData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IPersonsCache {
    fun getPerson(housesData: HousesData): Single<List<PersonData>>
    fun putPerson(housesData: HousesData, personData : List<PersonData>) : Completable
}