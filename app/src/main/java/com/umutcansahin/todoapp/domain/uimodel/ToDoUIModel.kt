package com.umutcansahin.todoapp.domain.uimodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
data class ToDoUIModel(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val isDone: Boolean,
    val note: String,
    val timestamp: Date,
    val categoryColor: String
): Parcelable

