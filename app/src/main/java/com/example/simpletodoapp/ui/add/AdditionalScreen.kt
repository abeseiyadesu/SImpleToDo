package com.example.simpletodoapp.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.simpletodoapp.data.Todo
import com.example.simpletodoapp.data.TodoViewModel

// タスク追加画面
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdditionalScreen(
    navController: NavController,
    onBackClick: () -> Unit,
    todoViewModel: TodoViewModel = viewModel(),
) {
    // タスクを入力する
    var inputValue by remember { mutableStateOf("") }
    var errorState by remember { mutableStateOf(false) }

    Scaffold(
        //　トップバー
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Simple Todo")
                },
                // 左上 戻るボタン
                navigationIcon = {
                    // ホーム画面へ移動
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "ArrowBackIcon "
                        )
                    }
                }
            )

        },
        // メインコンテンツ
        content = { paddingValues ->
            Column(
                // トップバー分を空ける
                modifier = Modifier.padding(paddingValues)
            ) {
                // タスク入力欄
                OutlinedTextField(
                    value = inputValue,
                    onValueChange = {
                        inputValue = it     // 入力値更新
                        errorState = inputValue.isBlank()
                    },
                    label = { Text("タスクを入力") },
                    isError = errorState,
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                )
                if (errorState) {
                    Text(
                        text = "入力は必須です。",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(start = 20.dp, top = 4.dp)
                    )
                }

                // 追加ボタン
                Button(
                    onClick = {
                        // 入力されたタスクが 空白 かどうか確認
                        if (inputValue.isNotBlank()) {
                            // 新規タスクを作成
                            val todo = Todo(
                                id = 0,
                                title = inputValue,
                                isCompleted = false
                            )
                            // 新規タスク を 追加
                            // データは永続化される
                            todoViewModel.insertTodo(todo)
                            inputValue = ""

                            // ホーム画面へ
                            navController.navigate("home")
                        } else {
                            errorState = true
                        }
                    },
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "追加")
                }
            }
        }
    )
}
