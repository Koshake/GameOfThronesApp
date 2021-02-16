package com.koshake1.gameofthronesapp.mvp.model.room.dao

import androidx.room.*
import com.koshake1.gameofthronesapp.mvp.model.room.entity.RoomPersonData
import com.koshake1.gameofthronesapp.mvp.model.room.entity.RoomQuotesData

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: RoomPersonData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg persons: RoomPersonData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(persons: List<RoomPersonData>)

    @Update
    fun update(person: RoomPersonData)

    @Update
    fun update(vararg persons: RoomPersonData)

    @Update
    fun update(persons: List<RoomPersonData>)

    @Delete
    fun delete(person: RoomPersonData)

    @Delete
    fun delete(vararg persons: RoomPersonData)

    @Delete
    fun delete(persons: List<RoomPersonData>)

    @Query("SELECT * FROM RoomPersonData")
    fun getAll(): List<RoomPersonData>

    @Query("SELECT * FROM RoomPersonData WHERE personId = :personId LIMIT 1")
    fun findForHouse(personId: String): List<RoomPersonData>
}