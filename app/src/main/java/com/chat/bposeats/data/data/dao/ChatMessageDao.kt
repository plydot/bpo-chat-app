package com.chat.bposeats.data.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chat.bposeats.data.data.entity.ChatMessage
import com.stfalcon.chatkit.commons.models.IUser

@Dao
interface ChatMessageDao {
    @Query("SELECT * FROM ChatMessages")
    fun getAll(): List<ChatMessage>

    @Query("SELECT * FROM ChatMessages")
    fun getLiveData(): LiveData<List<ChatMessage>>

    @Query("SELECT * FROM ChatMessages WHERE dbUserId IN (:ids)")
    fun dialogMessages(ids: List<String?>?): LiveData<List<ChatMessage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg message: ChatMessage)

    @Query("SELECT * FROM ChatMessages WHERE dbId = :id")
    fun getMessageById(id: String) : LiveData<List<ChatMessage>>

    @Query("DELETE FROM ChatMessages WHERE dbId LIKE :string")
    fun deleteItem(string: String)

    @Delete
    fun delete(message: ChatMessage)

    @Query("DELETE FROM ChatMessages")
    fun delete()

    @Update
    fun updateTodo(vararg message: ChatMessage)
}