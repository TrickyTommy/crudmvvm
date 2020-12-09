package com.example.crudmvvm.model

import com.google.gson.annotations.SerializedName

data class TodoResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("completed")
    val completed: Boolean,

    @SerializedName("title")
    val title: String,

    @SerializedName("userId")
    val userId: Int
)

fun TodoResponse.toModel() = TodoModel(id, completed, title, userId)