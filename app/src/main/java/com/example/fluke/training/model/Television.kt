package com.example.fluke.training.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

@Parcel
data class Television(@SerializedName("id") val id: String, @SerializedName("name") val name: String
                      , @SerializedName("backdrop_path") val backdrop_path: String, @SerializedName("poster_path") val poster_path: String
                      , @SerializedName("overview") val overview: String
                      , @SerializedName("vote_average") val vote_average: String) : Parcelable {
    constructor(parcel: android.os.Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: android.os.Parcel, flags: Int) {
        parcel.apply {
            writeString(id)
            writeString(name)
            writeString(backdrop_path)
            writeString(poster_path)
            writeString(overview)
            writeString(vote_average)
        }
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Television> {
        override fun createFromParcel(parcel: android.os.Parcel): Television = Television(parcel)
        override fun newArray(size: Int): Array<Television?> = arrayOfNulls(size)
    }
}

@Parcel
data class Episode(@SerializedName("air_date") val air_date: String
                   , @SerializedName("name") val number: String
                   , @SerializedName("overview") val overview: String
                   , @SerializedName("still_path") val still_path: String):Parcelable{
    constructor(parcel: android.os.Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: android.os.Parcel, flags: Int) {
        parcel.writeString(air_date)
        parcel.writeString(number)
        parcel.writeString(overview)
        parcel.writeString(still_path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Episode> {
        override fun createFromParcel(parcel: android.os.Parcel): Episode {
            return Episode(parcel)
        }

        override fun newArray(size: Int): Array<Episode?> {
            return arrayOfNulls(size)
        }
    }
}

@Parcel
data class TelevisionList(@SerializedName("results") var results: List<Television>? = null
                          , @SerializedName("page") val page: String? = null
                          , @SerializedName("total_pages") var total_pages: String? = null)

@Parcel
data class TelevisionType(@SerializedName("id") val type_id: String, @SerializedName("name") val type_name: String)

@Parcel
data class TelevisionTypeList(@SerializedName("genres") var type: List<TelevisionType>)

@Parcel
data class TelevisionDetail(@SerializedName("number_of_seasons") var numberOfSeason: String)

@Parcel
data class TelevisionSeason(@SerializedName("episodes") var episode: List<Episode>)