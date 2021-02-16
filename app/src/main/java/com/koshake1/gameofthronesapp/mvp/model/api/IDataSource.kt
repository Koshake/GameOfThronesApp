package com.koshake1.gameofthronesapp.mvp.model.api

import com.koshake1.gameofthronesapp.mvp.model.entity.HousesData
import com.koshake1.gameofthronesapp.mvp.model.entity.PersonData
import com.koshake1.gameofthronesapp.mvp.model.entity.QuotesData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IDataSource {
    @GET("v1/houses")
    fun getHouses(): Single<List<HousesData>>

    @GET("v1/house/{slug}")
    fun getCharacters(@Path("slug") slug: String): Single<List<PersonData>>

    @GET("v1/character/{slug}")
    fun getQuotes(@Path("slug") slug: String): Single<List<QuotesData>>
}