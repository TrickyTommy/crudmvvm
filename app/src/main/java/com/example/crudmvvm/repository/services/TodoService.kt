package com.example.crudmvvm.repository.services

import com.example.crudmvvm.model.ResponseModel
import retrofit2.http.GET

interface TodoService {
    @GET("todos")
    suspend fun getAllTodo():List<ResponseModel>


}
