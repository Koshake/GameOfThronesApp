package com.koshake1.gameofthronesapp.di.modules

import androidx.room.Room
import com.koshake1.gameofthronesapp.App
import com.koshake1.gameofthronesapp.mvp.model.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()
}