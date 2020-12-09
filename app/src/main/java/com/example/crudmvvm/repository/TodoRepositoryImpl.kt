package com.example.crudmvvm.repository

import com.example.crudmvvm.model.TodoModel
import com.example.crudmvvm.model.TodoResponse
import com.example.crudmvvm.repository.request.TodoRequest
import com.example.crudmvvm.repository.services.TodoService

class TodoRepositoryImpl(private val service: TodoService): TodoRepository {
    override suspend fun getAllTodo(): List<TodoResponse> {
        return service.getAllTodo()
    }

    override suspend fun insertTodo(todoRequest: TodoRequest): TodoResponse {
        return  service.insertTodo(todoRequest)
    }

    override suspend fun updateTodo(id: Int, todoRequest: TodoRequest): TodoResponse {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTodo(id: Int): TodoResponse {
        TODO("Not yet implemented")
    }

}