package com.koshake1.gameofthronesapp.mvp.view.list

interface HouseItemView : IItemView {
    fun setName(text: String)
    fun loadEmblem(url: String)
}