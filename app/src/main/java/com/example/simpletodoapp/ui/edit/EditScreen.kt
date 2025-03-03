package com.example.simpletodoapp.ui.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.simpletodoapp.data.Todo
import com.example.simpletodoapp.data.TodoViewModel
import com.example.simpletodoapp.data.TodoRepository

// 編集画面
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    navController: NavController,
    onBackClick: () -> Unit,
    todoViewModel: TodoViewModel = viewModel(),
    todoId: Int
) {
    // StateFlow から タイトルを監視
    val todoTitleState by todoViewModel.todoTitle.collectAsState()

    var inputValue by remember { mutableStateOf("") }

    // EditScreenが開かれた時 タイトルを取得
    LaunchedEffect(todoId) {
        todoViewModel.GetTodoTitleById(todoId)
    }

    // 取得したタイトルがある場合、初期入力値として設定
    LaunchedEffect(todoTitleState) {
        if (inputValue.isEmpty() && todoTitleState != null) {
            inputValue = todoTitleState ?: ""
        }
    }

    // 全体
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Simple Todo")
                },
                // ホーム画面へ移動
                // 左上 戻るボタン
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        // メインコンテンツ
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                // タスク編集欄
                OutlinedTextField(
                    value = inputValue,
                    onValueChange = { inputValue = it },
                    label = { Text("変更を入力") },
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                )

                // 編集内容の変更を 完了（確定）するボタン
                Button(
                    onClick = {
                        //　変更された要素をdatabaseに入れる処理を書く
                        val updatedTodo = Todo(
                            id = todoId,
                            title = inputValue,
                            isCompleted = false
                        )

                        todoViewModel.updateTodo(updatedTodo)
                        // ホーム画面へ
                        navController.navigate("home")
                    },
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "保存")
                }

                // 削除ボタン
                Button(
                    onClick = {
                        // 表示されてる値を消す処理をここに書く
                        // アラート表示
                        todoViewModel.deleteTodo(todoId)

                        // ポップアップで説明がありホーム画面へ
                        navController.navigate("home")
                    },
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "削除")
                }
            }
        }
    )
}

