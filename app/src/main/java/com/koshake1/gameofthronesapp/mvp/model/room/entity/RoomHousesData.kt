package com.koshake1.gameofthronesapp.mvp.model.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class RoomHousesData(
    @PrimaryKey() var slug: String,
    var name: String,
)