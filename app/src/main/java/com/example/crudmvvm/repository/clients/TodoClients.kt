package com.example.crudmvvm.repository.clients

import com.example.crudmvvm.repository.services.TodoService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TodoClients {
    companion object {
        private const val Base_url = "https://jsonplaceholder.typicode.com/"
        private const val base_url_api = "$Base_url/todos/"

        val service: TodoService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(base_url_api)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setLenient().create()
                    )
                )
                .build()

            retrofit.create(TodoService::class.java)
        }
    }
}