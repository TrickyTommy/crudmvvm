package com.example.crudmvvm.repository

import com.example.crudmvvm.repository.remote.request.TodoRequest
import com.example.crudmvvm.repository.remote.responses.TodoResponse

interface TodoRepository {
    suspend fun getAllTodo():List<TodoResponse>
    suspend fun insertTodo(todoRequest: TodoRequest): TodoResponse
    suspend fun updateTodo(id: Int, todoRequest: TodoRequest): TodoResponse
    suspend fun deleteTodo(id: Int): TodoResponse


}