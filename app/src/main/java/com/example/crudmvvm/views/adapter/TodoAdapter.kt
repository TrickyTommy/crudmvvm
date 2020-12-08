package com.example.crudmvvm.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crudmvvm.databinding.TodoItemBinding
import com.example.crudmvvm.model.TodoModel

class TodoAdapter(private val context: Context) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindata(todoModel: TodoModel) {
            binding.run {
                IdTodo.text = "Id : ${todoModel.id}"
                tvTittle.text = "Tittle : ${todoModel.title}"
                tvStatus.text = "Complete ; ${todoModel.completed}"


            }
        }

    }


    var list = mutableListOf<TodoModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TodoItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindata(list[position])
    }

    override fun getItemCount(): Int = list.size
}

