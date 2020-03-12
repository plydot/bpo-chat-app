package com.plydot.sms.bulksms.datastore.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chat.bposeats.data.data.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM Users")
    fun getAll(): List<User>

    @Query("SELECT * FROM Users")
    fun getLiveData(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: User)

    @Query("DELETE FROM Users WHERE id LIKE :string")
    fun deleteItem(string: String)

    @Delete
    fun delete(user: User)

    @Update
    fun updateTodo(vararg user: User)
}