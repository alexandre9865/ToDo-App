package com.umutcansahin.todoapp.domain.use_case

import com.umutcansahin.todoapp.domain.mapper.ToDoEntityMapper
import com.umutcansahin.todoapp.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetToDoByCategoryUseCase @Inject constructor(
    private val repository: ToDoRepository,
    private val mapper: ToDoEntityMapper
) {

    operator fun invoke(categoryId: Int) = repository.getToDoByCategory(categoryId = categoryId).map { toDoList->
        toDoList.map {
            mapper.map(entity = it)
        }

    }
}
