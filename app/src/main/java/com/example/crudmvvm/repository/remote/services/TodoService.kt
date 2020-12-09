package com.example.crudmvvm.repository.remote.services

import com.example.crudmvvm.repository.remote.request.TodoRequest
import com.example.crudmvvm.repository.remote.responses.TodoResponse
import retrofit2.http.*

interface TodoService {
    @GET("todos")
    suspend fun getAllTodo():List<TodoResponse>

    @POST("todos")
    suspend fun insertTodo(@Body todoRequest: TodoRequest): TodoResponse

    @PUT("v1/todos/{id}")
    suspend fun updateTodoById(
        @Path("id") id: Int,
        @Body todoRequest: TodoRequest
    ): TodoResponse

    @DELETE("v1/todos/{id}")
    suspend fun deleteTodoById(@Path("id") id: Int): TodoResponse


}
