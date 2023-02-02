package com.umutcansahin.todoapp.domain.mapper

import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.domain.uimodel.ToDoUIModel

class ToDoEntityMapper {

    fun map(entity: ToDoEntity): ToDoUIModel {
        return entity.toUiModel()
    }

    private fun ToDoEntity.toUiModel() = ToDoUIModel(
        id = getId(),
        categoryId = getCategoryId(),
        name = getName(),
        isDone = getIsDone() ,
        note = getNote(),
        timestamp = getTimestamp(),
        categoryColor = getCategoryColor()
    )

    private fun ToDoEntity.getId() = id

    private fun ToDoEntity.getCategoryId() = categoryId

    private fun ToDoEntity.getName() = name

    private fun ToDoEntity.getIsDone() = isDone

    private fun ToDoEntity.getNote() = note.orEmpty()

    private fun ToDoEntity.getTimestamp() = timestamp

    private fun ToDoEntity.getCategoryColor() = when(categoryId) { //TODO - automate it
        1 -> "#FFA5D6A7" //business
        2 -> "#FFFFAB91" //school
        3 -> "#FF80DEEA" // shopping
        4 -> "#FFFFCC80" //sport
        else -> "#FFE4E4E4"
    }


}