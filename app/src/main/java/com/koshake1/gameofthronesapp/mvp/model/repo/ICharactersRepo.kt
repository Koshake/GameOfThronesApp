package com.koshake1.gameofthronesapp.mvp.model.repo

import com.koshake1.gameofthronesapp.mvp.model.entity.HousesData
import com.koshake1.gameofthronesapp.mvp.model.entity.PersonData
import io.reactivex.rxjava3.core.Single

interface ICharactersRepo {
    fun getCharacters(housesData: HousesData) : Single<List<PersonData>>
}