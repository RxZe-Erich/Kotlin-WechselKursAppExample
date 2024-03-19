package com.example.test.domain.models

import android.os.Parcel
import android.os.Parcelable

data class Coin(
    val id : String,
    val name : String,
    val symbol : String,
    val image : String,
    val market_cap : Long,
    val price : Double,
    val price_change_24h: Double,
    val price_precentage_change : Double,
    val low_price : Double,
    val high_price : Double,
    val currency : String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(symbol)
        parcel.writeString(image)
        parcel.writeLong(market_cap)
        parcel.writeDouble(price)
        parcel.writeDouble(price_change_24h)
        parcel.writeDouble(price_precentage_change)
        parcel.writeDouble(low_price)
        parcel.writeDouble(high_price)
        parcel.writeString(currency)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coin> {
        override fun createFromParcel(parcel: Parcel): Coin {
            return Coin(parcel)
        }

        override fun newArray(size: Int): Array<Coin?> {
            return arrayOfNulls(size)
        }
    }
}
