package com.umutcansahin.todoapp.data.local

import androidx.room.*
import java.util.*

@Entity(tableName = "category",
        foreignKeys = [ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("parentId"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )],
        indices=[
            Index(value = arrayOf("parentId"))
        ])
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "parentId") val parentId: Int?,
    @ColumnInfo(name = "color") val color: String
)
