package com.example.crudmvvm.repository.remote.request

class TodoRequest(
    val id : Int,

    val completed: Boolean = false,

    val title: String

    )
