package com.example.crudmvvm.repository.request

class TodoRequest(
    val id: Int,

    val completed: Boolean,

    val title: String,

    val userId: Int
)
