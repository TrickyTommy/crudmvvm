package com.example.crudmvvm.repository

import com.example.crudmvvm.model.TodoModel
import com.example.crudmvvm.model.TodoResponse
import com.example.crudmvvm.repository.request.TodoRequest

interface TodoRepository {
    suspend fun getAllTodo():List<TodoResponse>
    suspend fun insertTodo(todoRequest: TodoRequest): TodoResponse
    suspend fun updateTodo(id: Int, todoRequest: TodoRequest): TodoResponse
    suspend fun deleteTodo(id: Int): TodoResponse


}