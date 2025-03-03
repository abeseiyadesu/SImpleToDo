package com.example.simpletodoapp.data

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {
    fun getAllTodos(): LiveData<List<Todo>> = todoDao.getAllTodos()

    suspend fun insert(todo: Todo) = todoDao.insert(todo)

    suspend fun update(todo: Todo) = todoDao.update(todo)

    suspend fun delete(todo: Todo) = todoDao.delete(todo)

    suspend fun getTodoById(id: Int): Todo? {
        return todoDao.getTodoById(id)
    }

    suspend fun getTodoTitleById(todoId: Int): String? {
        return todoDao.getTodoTitleById(todoId)
    }
}