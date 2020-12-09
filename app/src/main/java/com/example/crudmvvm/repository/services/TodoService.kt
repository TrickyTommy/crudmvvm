package com.example.crudmvvm.repository.services

import com.example.crudmvvm.model.TodoModel
import com.example.crudmvvm.model.TodoResponse
import com.example.crudmvvm.repository.request.TodoRequest
import retrofit2.http.*

interface TodoService {
    @GET("todos")
    suspend fun getAllTodo():List<TodoResponse>

    @POST("todos")
    suspend fun insertTodo(@Body todoRequest: TodoRequest): TodoResponse

    @PUT("v1/todos/{id}")
    suspend fun updateTodoById(
        @Path("id") id: Long,
        @Body todoRequest: TodoRequest
    ): TodoResponse

    @DELETE("v1/todos/{id}")
    suspend fun deleteTodoById(@Path("id") id: Long): TodoResponse


}
