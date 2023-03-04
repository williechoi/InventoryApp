package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)  // Add suspend so that Dao runs on a separate thread.

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    // By using Flow as the return type, you receive notification
    // whenever the data in the database changes.
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>

}