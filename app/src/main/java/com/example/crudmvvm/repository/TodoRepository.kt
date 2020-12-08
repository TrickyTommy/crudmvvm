package com.example.crudmvvm.repository

import com.example.crudmvvm.model.ResponseModel
import com.example.crudmvvm.model.TodoModel
import com.example.crudmvvm.repository.request.TodoRequest

interface TodoRepository {
    suspend fun getAllTodo():List<ResponseModel>
    suspend fun insertTodo(todoRequest: TodoRequest): ResponseModel
    suspend fun updateTodo(id: Long, todoRequest: TodoRequest): ResponseModel
    suspend fun deleteTodo(id: Long): ResponseModel


}