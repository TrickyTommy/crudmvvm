package com.example.crudmvvm.viewmodels

import com.example.crudmvvm.model.ResponseModel
import com.example.crudmvvm.model.TodoModel
import java.lang.Exception

sealed class StatesTodo {
    data class Loading(val message: String = "mohon tonggu"): StatesTodo()
    data class error(val exception: Exception) : StatesTodo()
    data class  SucceseGetTodo(val list: List<TodoModel>): StatesTodo()
    data class SuccessInsert(val todo: TodoModel): StatesTodo()

}