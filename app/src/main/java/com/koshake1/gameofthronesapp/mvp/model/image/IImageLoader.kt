package com.koshake1.gameofthronesapp.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}