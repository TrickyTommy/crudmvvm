package com.example.crudmvvm.viewmodels

import androidx.lifecycle.*
import com.example.crudmvvm.model.toModel
import com.example.crudmvvm.repository.TodoRepository
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

    private fun onError(ex: Exception) {
        ex.printStackTrace()
        mutabaleState.postValue(StatesTodo.error(ex))

    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("Not yet implemented")
    }
}