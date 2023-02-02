package com.umutcansahin.todoapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT *  FROM category ORDER BY id DESC")
    fun getAllCategory(): Flow<List<CategoryEntity>>

    @Query("SELECT *  FROM category WHERE name = :name")
    fun getCategoryByName(name: String): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getCategoryById(id: Int): CategoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(toDo: CategoryEntity)

    @Delete
    suspend fun deleteCategory(toDo: CategoryEntity)

    @Update
    suspend fun updateCategory(toDo: CategoryEntity)
}