package com.umutcansahin.todoapp.domain.use_case

import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.domain.repository.ToDoRepository
import java.util.Date
import javax.inject.Inject

class InsertOrUpdateToDoUseCase @Inject constructor(
    private val repository: ToDoRepository
) {

    suspend operator fun invoke(
        name: String,
        isInsert: Boolean,
        id: Int?,
        categoryId: Int?,
        note: String?,
        date: Date,
        isDone: Boolean
    ) {
        categoryId?.let {

            if (isInsert && name.isNotBlank()) {

                repository.insertToDo(
                    toDo = ToDoEntity(
                        name = name,
                        id = id!!,
                        categoryId = categoryId,
                        note = note,
                        timestamp = date,
                        isDone = false
                    )
                )

            } else if (!isInsert && name.isNotBlank()) {
                repository.updateToDo(
                    toDo = ToDoEntity(
                        name = name,
                        id = id!!,
                        categoryId = categoryId,
                        note = note,
                        timestamp = date,
                        isDone = isDone
                    )
                )
            }
        }
    }
}