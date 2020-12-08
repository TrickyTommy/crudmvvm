package com.example.crudmvvm.repository

import com.example.crudmvvm.model.ResponseModel
import com.example.crudmvvm.repository.request.TodoRequest
import com.example.crudmvvm.repository.services.TodoService

class TodoRepositoryImpl(private val service: TodoService): TodoRepository {
    override suspend fun getAllTodo(): List<ResponseModel> {
        return service.getAllTodo()
    }

    override suspend fun insertTodo(todoRequest: TodoRequest): ResponseModel {
        return  service.insertTodo(todoRequest)
    }

    override suspend fun updateTodo(id: Long, todoRequest: TodoRequest): ResponseModel {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTodo(id: Long): ResponseModel {
        TODO("Not yet implemented")
    }

}