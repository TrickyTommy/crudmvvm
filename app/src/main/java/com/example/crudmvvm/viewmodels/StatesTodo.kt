package com.example.crudmvvm.viewmodels

import com.example.crudmvvm.model.TodoModel

sealed class StatesTodo {
    data class Loading(val message: String = "mohon tonggu"): StatesTodo()
    data class error(val exception: Exception) : StatesTodo()
    data class  SucceseGetTodo(val list: List<TodoModel>): StatesTodo()
    data class SuccessInsert(val model: TodoModel): StatesTodo()
    data class SuccessUpdateTodo(val todo: TodoModel) : StatesTodo()
    data class SuccessDeleteTodo(val id : Int) : StatesTodo()

}