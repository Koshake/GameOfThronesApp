package com.koshake1.gameofthronesapp.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class PersonData(
    @Expose val name: String,
    @Expose val slug: String
) : Parcelable