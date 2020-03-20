package com.chat.bposeats.data.data.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.chat.bposeats.data.data.dao.convert.IMessageConverter
import com.chat.bposeats.data.data.dao.convert.IUserListConverter
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser

@Entity(tableName = "ChatDialogs")
@TypeConverters(IMessageConverter::class, IUserListConverter::class)
data class ChatDialog(
    @PrimaryKey var dbId : String,
    var dbDialogPhoto: String?,
    var dbUnreadCount: Int,
    var dbLastMessage: ChatMessage?,
    var dbDialogName: String,
    var dbUsers: MutableList<User>
): IDialog<IMessage>, Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readInt(),
        parcel.readParcelable(ChatMessage::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readSerializable() as MutableList<User>
    )

    override fun getDialogPhoto(): String {
        return dbDialogPhoto!!
    }

    override fun getUnreadCount(): Int {
        return dbUnreadCount
    }

    override fun setLastMessage(lastMessage: IMessage?) {
        dbLastMessage = lastMessage!! as ChatMessage
    }

    override fun getId(): String {
        return dbId
    }

    override fun getUsers(): MutableList<out IUser> {
        return dbUsers
    }

    override fun getLastMessage(): IMessage {
        return dbLastMessage!!
    }

    override fun getDialogName(): String {
        return if (dbDialogName.isEmpty()){
            var name = ""
            for (user: IUser in dbUsers){
                name += "${user.name} "
            }
            name
        }else{
            dbDialogName
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dbId)
        parcel.writeString(dbDialogPhoto)
        parcel.writeInt(dbUnreadCount)
        parcel.writeString(dbDialogName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChatDialog> {
        override fun createFromParcel(parcel: Parcel): ChatDialog {
            return ChatDialog(parcel)
        }

        override fun newArray(size: Int): Array<ChatDialog?> {
            return arrayOfNulls(size)
        }
    }

}