package com.koshake1.gameofthronesapp.mvp.model.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.koshake1.gameofthronesapp.mvp.model.room.converter.QuotesConverter
import com.koshake1.gameofthronesapp.mvp.model.room.dao.HousesDao
import com.koshake1.gameofthronesapp.mvp.model.room.dao.PersonDao
import com.koshake1.gameofthronesapp.mvp.model.room.dao.QuotesDao
import com.koshake1.gameofthronesapp.mvp.model.room.entity.RoomHousesData
import com.koshake1.gameofthronesapp.mvp.model.room.entity.RoomPersonData
import com.koshake1.gameofthronesapp.mvp.model.room.entity.RoomQuotesData
import java.lang.RuntimeException

@androidx.room.Database(entities = [RoomHousesData::class, RoomPersonData::class, RoomQuotesData::class], version = 1)
@TypeConverters(QuotesConverter::class)
abstract class Database : RoomDatabase() {
    abstract val houseDao: HousesDao
    abstract  val quotesDao: QuotesDao
    abstract  val personDao: PersonDao
    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null

        fun getInstance() = instance ?: throw RuntimeException("База данных не создана")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME).build()
            }
        }
    }
}