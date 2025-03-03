package com.example.simpletodoapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch
import com.example.simpletodoapp.data.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository // インスタンス
    val allTodos: LiveData<List<Todo>>

    init {
        val todoDatabase = TodoDatabase.getDatabase(application)
        repository = TodoRepository(todoDatabase.todoDao())
        allTodos = repository.getAllTodos()
    }

    // title を保持する StateFlow
    private val _todoTitle = MutableStateFlow<String?>(null)
    val todoTitle: StateFlow<String?> = _todoTitle

    // title を取得する関数
    fun GetTodoTitleById(todoId: Int) {
        viewModelScope.launch {
            val title = repository.getTodoTitleById(todoId)
            _todoTitle.value = title
        }
    }

    fun insertTodo(todo: Todo) = viewModelScope.launch {
        repository.insert(todo)
    }


    // 内容更新する
    fun updateTodo(todo: Todo) = viewModelScope.launch {
        repository.update(todo)
    }

    fun deleteTodo(todoId: Int) = viewModelScope.launch {
        val todo = repository.getTodoById(todoId)
        if (todo != null) {
            repository.delete(todo)
        }
    }

}