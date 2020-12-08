package com.example.crudmvvm.views.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.crudmvvm.databinding.FragmentTodoBinding
import com.example.crudmvvm.repository.TodoRepository
import com.example.crudmvvm.repository.TodoRepositoryImpl
import com.example.crudmvvm.repository.clients.TodoClients
import com.example.crudmvvm.repository.services.TodoService
import com.example.crudmvvm.viewmodels.StatesTodo
import com.example.crudmvvm.viewmodels.TodoModelFactory
import com.example.crudmvvm.viewmodels.getTodoViewModel
import com.example.crudmvvm.views.adapter.TodoAdapter
import kotlinx.android.synthetic.main.fragment_todo.*


class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding

    private val adapter by lazy { TodoAdapter(requireContext()) }
    private val service by lazy { TodoClients.service }
    private val remoteRepo: TodoRepository by lazy { TodoRepositoryImpl(service) }
    private val viewModelFactory by lazy { getTodoViewModel(remoteRepo) }
    private val viewModel by viewModels<getTodoViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoBinding.inflate(inflater, container, false).apply {

            recyclerView.adapter = adapter

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