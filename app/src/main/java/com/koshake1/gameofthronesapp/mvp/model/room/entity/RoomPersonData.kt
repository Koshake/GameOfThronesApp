package com.koshake1.gameofthronesapp.mvp.model.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomHousesData::class,
        parentColumns = ["slug"],
        childColumns = ["personId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomPersonData(
    @PrimaryKey() var name: String,
    var slug: String,
    var personId: String,
)