package com.example.crudmvvm.views.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.crudmvvm.databinding.FragmentTodoBinding
import com.example.crudmvvm.model.TodoModel
import com.example.crudmvvm.repository.TodoRepository
import com.example.crudmvvm.repository.TodoRepositoryImpl
import com.example.crudmvvm.repository.clients.TodoClients
import com.example.crudmvvm.repository.request.TodoRequest
import com.example.crudmvvm.viewmodels.StatesTodo
import com.example.crudmvvm.viewmodels.TodoModelFactory
import com.example.crudmvvm.viewmodels.getTodoViewModel
import com.example.crudmvvm.views.adapter.TodoAdapter


class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding

    private val adapter by lazy { TodoAdapter(requireContext()) }
    private val service by lazy { TodoClients.service }
    private val remoteRepo: TodoRepository by lazy { TodoRepositoryImpl(service) }
    private val viewModelFactory by lazy { TodoModelFactory(remoteRepo) }
    private val viewModel by viewModels<getTodoViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoBinding.inflate(inflater, container, false).apply {

            recyclerView.adapter = adapter
            btnTambah.setOnClickListener {
                if (tieTambah.text.toString().isNotEmpty()){
                    viewModel.insertTodo(TodoModel(title = tieTambah.text.toString()))
                }

            }

            viewModel.state.observe(viewLifecycleOwner) {
                when (it) {
                    is StatesTodo.Loading -> showLoading(true)
                    is StatesTodo.error -> {
                        showLoading(false)
                        Toast.makeText(
                            requireContext(),
                            it.exception.message ?: "Oops Somethink Wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is StatesTodo.SucceseGetTodo -> {
                        showLoading(false)
                        adapter.list = it.list.toMutableList()

                    }
                    is StatesTodo.SuccessInsert -> {
                        showLoading(false)
//                        adapter.insertTodo(it.response)
                        Toast.makeText(requireContext(), "id = ${it.model.id} , berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                        tieTambah.setText("")
                    }
                    else -> throw Exception("Unsupported  state type")
                }
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllTodo()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}