package com.example.crudmvvm.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crudmvvm.databinding.TodoItemBinding
import com.example.crudmvvm.model.TodoModel

class TodoAdapter(private val context: Context, private val listener: TodoListener) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindata(todoModel: TodoModel) {
            binding.run {
                IdTodo.text = "Id : ${todoModel.id}"
                tvTittle.text = "Tittle : ${todoModel.title}"
                tvStatus.text = "Complete ; ${todoModel.completed}"
            }
        }

    }

    fun insertTodo(todoModel: TodoModel) {
        list.add(0, todoModel)
        notifyItemInserted(0)
    }

    fun updateTodo(todoModel: TodoModel) {
        val index = list.indexOfFirst { it.id == todoModel.id }
        if (index != -1) {
            list[index] = todoModel
            notifyItemChanged(index)
        }
    }

    fun deleteTodo(id: Int) {
        val index = list.indexOfFirst { it.id == id }
        if (index != -1) {
            list.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    interface TodoListener {
        fun onChange(todoModel: TodoModel)
        fun onDelete(todoModel: TodoModel)
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
        val model by lazy { list[position] }
        holder.bindata(model)
        holder.binding.run {
            ivdelete.setOnClickListener { listener.onDelete(model) }
            ivStatus.setOnClickListener { listener.onChange(model) }
        }

    }

    override fun getItemCount(): Int = list.size
}

