package com.koshake1.gameofthronesapp.mvp.model.room.dao

import androidx.room.*
import com.koshake1.gameofthronesapp.mvp.model.room.entity.RoomQuotesData

@Dao
interface QuotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(quote: RoomQuotesData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg quotes: RoomQuotesData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(quotes: List<RoomQuotesData>)

    @Update
    fun update(quotes: RoomQuotesData)

    @Update
    fun update(vararg quotes: RoomQuotesData)

    @Update
    fun update(quotes: List<RoomQuotesData>)

    @Delete
    fun delete(quote: RoomQuotesData)

    @Delete
    fun delete(vararg quotes: RoomQuotesData)

    @Delete
    fun delete(quotes: List<RoomQuotesData>)

    @Query("SELECT * FROM RoomQuotesData")
    fun getAll(): List<RoomQuotesData>

    @Query("SELECT * FROM RoomQuotesData WHERE name = :name LIMIT 1")
    fun findForPerson(name: String): List<RoomQuotesData>
}