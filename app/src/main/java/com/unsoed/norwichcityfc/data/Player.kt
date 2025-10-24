package com.unsoed.norwichcityfc.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("position") val position: String?,
    @SerializedName("dateOfBirth") val dateOfBirth: String?,
    @SerializedName("nationality") val nationality: String?
) : Parcelable