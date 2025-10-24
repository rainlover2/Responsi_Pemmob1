package com.unsoed.norwichcityfc.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("shortName") val shortName: String?,
    @SerializedName("crest") val crest: String?, // URL Logo
    @SerializedName("venue") val venue: String?, // Nama Stadion
    @SerializedName("coach") val coach: Coach?, // Objek Pelatih
    @SerializedName("squad") val squad: List<Player>? // Daftar Pemain
) : Parcelable