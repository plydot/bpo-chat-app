package com.chat.bposeats.data.data.dao

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

    @Query("SELECT * FROM Users WHERE current = :current")
    fun getCurrentUser(current: Boolean = true) : LiveData<List<User>>

    @Query("SELECT * FROM Users WHERE current = :current")
    fun getRawCurrentUser(current: Boolean = true) : List<User>

    @Query("SELECT * FROM Users WHERE phone = :phone")
    fun getUser(phone: String) : User?

    @Query("SELECT * FROM Users WHERE dbId = :id")
    fun getUserById(id: String) : User?

    @Query("DELETE FROM Users WHERE dbId LIKE :string")
    fun deleteItem(string: String)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM Users")
    fun delete()

    @Update
    fun updateTodo(vararg user: User)
}