package com.example.crudmvvm.viewmodels

import androidx.lifecycle.*
import com.example.crudmvvm.model.TodoModel
import com.example.crudmvvm.model.toRequest
import com.example.crudmvvm.repository.TodoRepository
import com.example.crudmvvm.repository.remote.responses.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class getTodoViewModel(private val repository: TodoRepository) : ViewModel(),
    ViewModelProvider.Factory {
    private val mutabaleState by lazy { MutableLiveData<StatesTodo>() }
    val state: LiveData<StatesTodo> get() = mutabaleState

    fun getAllTodo() {
        mutabaleState.value = StatesTodo.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val todorespon = repository.getAllTodo().asSequence().map { it.toModel() }.toList()
                mutabaleState.postValue(StatesTodo.SucceseGetTodo(todorespon))


            } catch (ex: Exception) {
                onError(ex)
            }
        }
    }

    fun insertTodo(model: TodoModel) {
        mutabaleState.value = StatesTodo.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = repository.insertTodo(model.toRequest())
                val model = request.toModel()
                mutabaleState.postValue(StatesTodo.SuccessInsert(model))
            } catch (exc: Exception) {
                onError(exc)
            }
        }
    }

    fun updateTodo(todoModel: TodoModel) {
        mutabaleState.value = StatesTodo.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val todoResponse = repository.updateTodo(todoModel.id, todoModel.toRequest())
                val todo = todoResponse.toModel()
                mutabaleState.postValue(StatesTodo.SuccessUpdateTodo(todo))
            } catch (exc: Exception) {
                onError(exc)
            }
        }
    }

    //    fun deleteTodo(todoModel: TodoModel) {
//        mutabaleState.value = StatesTodo.Loading()
//
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val todoResponse = repository.deleteTodo(todoModel.id)
//                val model = todoResponse.toModel()
//                mutabaleState.postValue(StatesTodo.SuccessDeleteTodo(model))
//            } catch (exc: Exception) {
//                onError(exc)
//            }
//        }
//    }
    fun deleteTodo(model: TodoModel) {
        mutabaleState.value = StatesTodo.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mutabaleState.postValue(StatesTodo.SuccessDeleteTodo(model.id))
            } catch (exc: Exception) {

            }
        }
    }


    private fun onError(ex: Exception) {
        ex.printStackTrace()
        mutabaleState.postValue(StatesTodo.error(ex))

    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return getTodoViewModel(repository) as T
    }
}