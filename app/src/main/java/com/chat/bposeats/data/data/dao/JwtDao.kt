package com.chat.bposeats.data.data.dao

import androidx.room.*
import com.chat.bposeats.data.data.entity.JwtToken

@Dao
interface JwtDao {

    @Query("SELECT * FROM Token")
    fun getAll(): List<JwtToken>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg jwt: JwtToken)

    @Delete
    fun delete(jwt: JwtToken)

    @Query("DELETE FROM Token")
    fun delete()
}