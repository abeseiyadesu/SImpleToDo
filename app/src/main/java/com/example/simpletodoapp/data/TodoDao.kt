package com.example.simpletodoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Insert
    suspend fun insert(todo: Todo)

    @Query("SELECT * FROM Todo")
    fun getAllTodos(): LiveData<List<Todo>>

    @Query("SELECT * FROM Todo WHERE id = :id")
    suspend fun getTodoById(id: Int): Todo?

    @Query("SELECT title FROM Todo WHERE id = :todoId")
    suspend fun getTodoTitleById(todoId: Int): String?

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)
}