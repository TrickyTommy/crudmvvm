package com.example.crudmvvm.di

import com.example.crudmvvm.repository.TodoRepository
import com.example.crudmvvm.repository.remote.TodoRepositoryImpl
import com.example.crudmvvm.repository.remote.services.TodoService
import com.example.crudmvvm.viewmodels.TodoViewModel
import com.google.gson.GsonBuilder
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    fun providePostService(retrofit: Retrofit): TodoService {
        return retrofit.create(TodoService::class.java)
    }
    single { providePostService(get()) }
}
val networkModule = module {
    fun provideRetrofit(BASE_URL: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }
    single {
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        provideRetrofit(BASE_URL)
    }
}
val repositoryModule = module {
    fun providePostsRepositoryModule(api: TodoService): TodoRepository {
        return TodoRepositoryImpl(api)
    }
    single { providePostsRepositoryModule(get()) }
}
val viewModelModule = module {
    viewModel{
        TodoViewModel(repository =  get ())
    }
}