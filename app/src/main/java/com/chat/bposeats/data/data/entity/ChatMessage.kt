package com.chat.bposeats.data.data.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.chat.bposeats.data.data.dao.convert.DateConverter
import com.chat.bposeats.data.data.dao.convert.IUserConverter
import com.chat.bposeats.data.data.dao.convert.IUserListConverter
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.io.Serializable
import java.util.*

@Entity(tableName = "ChatMessages")
@TypeConverters(DateConverter::class, IUserConverter::class)
public data class ChatMessage(
    @PrimaryKey var dbId : String,
    var name: String,
    var message: String,
    var dateCreated: Date,
    var dbUser: User,
    var dbUserId : String
) : IMessage, Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readSerializable() as Date,
        parcel.readParcelable(User::class.java.classLoader)!!,
        parcel.readString()!!
    )

    override fun getId(): String {
        return dbId
    }

    override fun getCreatedAt(): Date {
        return dateCreated
    }

    override fun getUser(): IUser {
        return dbUser
    }

    override fun getText(): String {
        return message
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dbId)
        parcel.writeString(name)
        parcel.writeString(message)
        parcel.writeString(dbUserId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChatMessage> {
        override fun createFromParcel(parcel: Parcel): ChatMessage {
            return ChatMessage(parcel)
        }

        override fun newArray(size: Int): Array<ChatMessage?> {
            return arrayOfNulls(size)
        }
    }
}

