package com.koshake1.gameofthronesapp.mvp.view.list

interface CharacterItemView : IItemView {
    fun setName(text : String)
    fun setSlug(text : String)
}