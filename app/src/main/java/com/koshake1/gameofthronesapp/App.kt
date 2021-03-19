package com.koshake1.gameofthronesapp

import android.app.Application
import com.koshake1.gameofthronesapp.di.AppComponent
import com.koshake1.gameofthronesapp.di.DaggerAppComponent
import com.koshake1.gameofthronesapp.di.character.CharacterSubComponent
import com.koshake1.gameofthronesapp.di.house.HouseSubComponent
import com.koshake1.gameofthronesapp.di.modules.AppModule
import com.koshake1.gameofthronesapp.di.quote.QuoteSubComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set
    var houseSubComponent: HouseSubComponent? = null
        private set
    var quoteSubComponent: QuoteSubComponent? = null
        private set
    var characterSubComponent: CharacterSubComponent? = null
        private set

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initHouseSubComponent() = appComponent.houseSubComponent().also { houseSubComponent = it }

    fun initQuoteSubComponent() = houseSubComponent?.quoteSubComponent().also {
        quoteSubComponent = it
    }

    fun initCharacterSubComponent() = houseSubComponent?.characterSubComponent().also { characterSubComponent = it }

    fun releaseHouseSubComponent() {
        houseSubComponent = null
    }

    fun releaseQuoteSubComponent() {
        quoteSubComponent = null
    }

    fun releaseCharacterSubComponent() {
        characterSubComponent = null
    }
}