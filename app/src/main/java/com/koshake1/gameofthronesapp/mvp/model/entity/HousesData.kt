package com.koshake1.gameofthronesapp.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.android.parcel.Parcelize

@Parcelize
class HousesData(
    @Expose val slug: String,
    @Expose val name: String,
    @Expose val members: List<PersonData>,
) : Parcelable