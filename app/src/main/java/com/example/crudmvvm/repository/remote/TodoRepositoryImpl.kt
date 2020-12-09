package com.example.crudmvvm.repository.remote

import com.example.crudmvvm.repository.TodoRepository
import com.example.crudmvvm.repository.remote.request.TodoRequest
import com.example.crudmvvm.repository.remote.responses.TodoResponse
import com.example.crudmvvm.repository.remote.services.TodoService

class TodoRepositoryImpl(private val service: TodoService): TodoRepository {
    override suspend fun getAllTodo(): List<TodoResponse> {
        return service.getAllTodo()
    }

    override suspend fun insertTodo(todoRequest: TodoRequest): TodoResponse {
        return  service.insertTodo(todoRequest)
    }

    override suspend fun updateTodo(id: Int, todoRequest: TodoRequest): TodoResponse  = service.updateTodoById(id, todoRequest)

    override suspend fun deleteTodo(id: Int): TodoResponse = service.deleteTodoById(id)


}