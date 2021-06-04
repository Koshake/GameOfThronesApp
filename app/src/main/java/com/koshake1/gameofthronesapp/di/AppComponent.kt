package com.koshake1.gameofthronesapp.di

import com.koshake1.gameofthronesapp.di.house.HouseSubComponent
import com.koshake1.gameofthronesapp.di.modules.ApiModule
import com.koshake1.gameofthronesapp.di.modules.AppModule
import com.koshake1.gameofthronesapp.di.modules.CiceroneModule
import com.koshake1.gameofthronesapp.di.modules.DatabaseModule
import com.koshake1.gameofthronesapp.mvp.presenter.MainPresenter
import com.koshake1.gameofthronesapp.mvp.presenter.SingleQuotePresenter
import com.koshake1.gameofthronesapp.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
    ]
)
interface AppComponent {
    fun houseSubComponent() : HouseSubComponent
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(singleQuotePresenter: SingleQuotePresenter)
}