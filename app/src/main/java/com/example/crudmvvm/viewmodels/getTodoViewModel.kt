package com.example.crudmvvm.viewmodels

import androidx.lifecycle.*
import com.example.crudmvvm.model.TodoModel
import com.example.crudmvvm.model.toModel
import com.example.crudmvvm.model.toRequest
import com.example.crudmvvm.repository.TodoRepository
import com.example.crudmvvm.repository.request.TodoRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class getTodoViewModel (private val repository: TodoRepository): ViewModel(),
    ViewModelProvider.Factory {
    private  val mutabaleState by lazy {MutableLiveData<StatesTodo>()}
    val state: LiveData<StatesTodo> get() = mutabaleState

    fun getAllTodo(){
        mutabaleState.value = StatesTodo.Loading()

        viewModelScope.launch (Dispatchers.IO){
            try {
                val todorespon =  repository.getAllTodo().asSequence().map { it.toModel() }.toList()
                mutabaleState.postValue(StatesTodo.SucceseGetTodo(todorespon))


            }catch (ex:Exception){
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

    private fun onError(ex: Exception) {
        ex.printStackTrace()
        mutabaleState.postValue(StatesTodo.error(ex))

    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return getTodoViewModel(repository)as T
    }
}