package com.chat.bposeats.data.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chat.bposeats.data.data.entity.ChatDialog
import com.chat.bposeats.data.data.entity.ChatMessage
import com.chat.bposeats.data.data.entity.User

@Dao
interface ChatDialogDao {
    @Query("SELECT * FROM ChatDialogs")
    fun getAll(): List<ChatDialog>

    @Query("SELECT * FROM ChatDialogs")
    fun getLiveData(): LiveData<List<ChatDialog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg message: ChatDialog)

    @Query("SELECT * FROM ChatDialogs WHERE dbId = :id")
    fun getDialogById(id: String) : LiveData<List<ChatDialog>>

    @Query("DELETE FROM ChatDialogs WHERE dbId LIKE :string")
    fun deleteItem(string: String)

    @Delete
    fun delete(message: ChatDialog)

    @Query("DELETE FROM ChatDialogs")
    fun delete()

    @Update
    fun updateTodo(vararg message: ChatDialog)
}