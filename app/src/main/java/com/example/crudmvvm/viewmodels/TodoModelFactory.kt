package com.example.crudmvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crudmvvm.repository.TodoRepository

class TodoModelFactory(private val factoryrepo: TodoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodoViewModel(factoryrepo) as T
    }
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return TodoModelFactory(factoryrepo) as T
//    }


}
