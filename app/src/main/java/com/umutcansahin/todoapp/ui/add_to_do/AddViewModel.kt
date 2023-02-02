package com.umutcansahin.todoapp.ui.add_to_do

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.todoapp.domain.uimodel.ToDoUIModel
import com.umutcansahin.todoapp.domain.use_case.GetSingleToDoUseCase
import com.umutcansahin.todoapp.domain.use_case.InsertOrUpdateToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val insertOrUpdateToDoUseCase: InsertOrUpdateToDoUseCase,
    private val getSingleToDoUseCase: GetSingleToDoUseCase
) : ViewModel(){

    private val _singleToDo = MutableLiveData<ToDoUIModel>()
    val singleToDo: LiveData<ToDoUIModel> = _singleToDo

    fun insertOrUpdate(name: String,isInsert: Boolean,id: Int?,categoryId: Int?,note: String?,date: Date) {
        viewModelScope.launch(Dispatchers.IO) {

            insertOrUpdateToDoUseCase(
                name = name,
                isInsert = isInsert,
                id = id,
                categoryId = categoryId,
                note = note,
                date = date,
                isDone = false
            )
        }
    }

    fun getById(id: Int?) {
        viewModelScope.launch {
            if (id != 0 && id != null) {
                _singleToDo.value = getSingleToDoUseCase.invoke(id = id)
            }
        }
    }
}