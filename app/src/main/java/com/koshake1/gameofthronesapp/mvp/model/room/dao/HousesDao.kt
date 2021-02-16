package com.koshake1.gameofthronesapp.mvp.model.room.dao

import androidx.room.*

import com.koshake1.gameofthronesapp.mvp.model.room.entity.RoomHousesData

@Dao
interface HousesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(house: RoomHousesData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg houses: RoomHousesData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(houses: List<RoomHousesData>)

    @Update
    fun update(houses: RoomHousesData)

    @Update
    fun update(vararg houses: RoomHousesData)

    @Update
    fun update(houses: List<RoomHousesData>)

    @Delete
    fun delete(houses: RoomHousesData)

    @Delete
    fun delete(vararg houses: RoomHousesData)

    @Delete
    fun delete(houses: List<RoomHousesData>)

    @Query("SELECT * FROM RoomHousesData")
    fun getAll(): List<RoomHousesData>

    @Query("SELECT * FROM RoomHousesData WHERE name = :name LIMIT 1")
    fun findByName(name: String): RoomHousesData
}