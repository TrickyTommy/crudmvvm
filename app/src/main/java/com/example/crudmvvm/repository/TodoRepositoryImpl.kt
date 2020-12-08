package com.example.crudmvvm.repository

import com.example.crudmvvm.model.ResponseModel
import com.example.crudmvvm.repository.services.TodoService

class TodoRepositoryImpl(private val service: TodoService): TodoRepository {
    override suspend fun getAllTodo(): List<ResponseModel> {
        return service.getAllTodo()
    }

}