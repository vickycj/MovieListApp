package com.vicky.apps.datapoints.ui.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


data class DetailModel(
    var key: String,
    var value: String

): Parcelable, Serializable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(key)
        parcel.writeString(value)

    }



    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailModel> {
        override fun createFromParcel(parcel: Parcel): DetailModel {
            return DetailModel(parcel)
        }

        override fun newArray(size: Int): Array<DetailModel?> {
            return arrayOfNulls(size)
        }
    }

}
