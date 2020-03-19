package com.chat.bposeats.data.data.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.stfalcon.chatkit.commons.models.IUser
import java.io.Serializable

@Entity(tableName = "Users", indices = [Index(value = ["phone"], unique = true)])
data class User(
    @SerializedName("id") @PrimaryKey var dbId: String,
    @SerializedName("last_name") var lastName: String,
    var current: Boolean,
    @SerializedName("phone") var phone: String,
    @SerializedName("first_name") var firstName: String,
    @SerializedName("avatar") var dbAvatar: String,
    var socketSio: String
) : IUser, Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun getAvatar(): String {
        return dbAvatar
    }

    override fun getName(): String {
        return firstName
    }

    override fun getId(): String {
        return dbId
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dbId)
        parcel.writeString(lastName)
        parcel.writeByte(if (current) 1 else 0)
        parcel.writeString(phone)
        parcel.writeString(firstName)
        parcel.writeString(dbAvatar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}