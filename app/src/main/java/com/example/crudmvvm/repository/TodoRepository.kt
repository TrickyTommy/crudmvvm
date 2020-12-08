package com.example.crudmvvm.repository

import com.example.crudmvvm.model.ResponseModel
import com.example.crudmvvm.model.TodoModel

interface TodoRepository {
    suspend fun getAllTodo(): List<ResponseModel>

}