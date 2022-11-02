package com.ayd.shoppingapp.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rating(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("rate")
    val rate: Double?
):Parcelable