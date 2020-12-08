package com.example.crudmvvm.repository.services

import com.example.crudmvvm.model.ResponseModel
import com.example.crudmvvm.repository.request.TodoRequest
import retrofit2.http.*

interface TodoService {
    @GET("todos")
    suspend fun getAllTodo():List<ResponseModel>

    @POST("todos")
    suspend fun insertTodo(@Body todoRequest: TodoRequest): ResponseModel

    @PUT("v1/todos/{id}")
    suspend fun updateTodoById(
        @Path("id") id: Long,
        @Body todoRequest: TodoRequest
    ): ResponseModel

    @DELETE("v1/todos/{id}")
    suspend fun deleteTodoById(@Path("id") id: Long): ResponseModel


}
