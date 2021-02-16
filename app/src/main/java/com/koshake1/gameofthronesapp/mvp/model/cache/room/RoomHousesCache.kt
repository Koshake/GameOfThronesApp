package com.koshake1.gameofthronesapp.mvp.model.cache.room

import com.koshake1.gameofthronesapp.mvp.model.cache.IHousesCache
import com.koshake1.gameofthronesapp.mvp.model.entity.HousesData
import com.koshake1.gameofthronesapp.mvp.model.entity.PersonData
import com.koshake1.gameofthronesapp.mvp.model.room.Database
import com.koshake1.gameofthronesapp.mvp.model.room.entity.RoomHousesData
import com.koshake1.gameofthronesapp.mvp.model.room.entity.RoomPersonData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomHousesCache(val db: Database) : IHousesCache {
    override fun getHouses(): Single<List<HousesData>> = Single.fromCallable {
        db.houseDao.getAll().map { roomHousesData ->
            HousesData(
                roomHousesData.slug,
                roomHousesData.name,
                db.personDao.findForHouse(roomHousesData.slug).map { roomPersonData ->
                    PersonData(roomPersonData.name, roomPersonData.slug)
                })
        }
    }

    override fun putHouses(housesData: List<HousesData>) =
        Completable.fromCallable {
            db.houseDao.insert(housesData.map { house ->
                RoomHousesData(
                    house.slug,
                    house.name,
                )
            }
            )
            for (house in housesData) {
                house.members.map {
                    RoomPersonData(it.name, it.slug, house.slug)
                }.apply { db.personDao.insert(this) }
            }
        }.subscribeOn(Schedulers.io())

}