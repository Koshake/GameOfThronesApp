package com.koshake1.gameofthronesapp.mvp.model.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomPersonData::class,
        parentColumns = ["name"],
        childColumns = ["quotesId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomQuotesData(
    @PrimaryKey() var name: String,
    var slug: String,
    var quotes: List<String>,
    var quotesId: String,
)