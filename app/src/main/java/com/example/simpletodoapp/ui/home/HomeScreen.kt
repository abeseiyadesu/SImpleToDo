package com.example.simpletodoapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.simpletodoapp.data.TodoViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController

// ホーム画面
@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    todoViewModel: TodoViewModel = viewModel()
) {
    // compose の 状態として
    // List表示
    val todos by todoViewModel.allTodos.observeAsState(initial = emptyList())

    Scaffold(
        // トップバー
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Simple Todo")
                }
            )
        },
        // タスク追加ボタン
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // 編集画面へ
                    navController.navigate("add")
                },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                contentColor = Color.White
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Localized description"
                )
            }
        },
        // メインコンテンツ
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ){
                LazyColumn(
                    modifier = Modifier.
                    padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 4.dp,
                        bottom = 4.dp
                    )            ) {
                    // 一覧表示
                    items(todos) { todo ->
                        Row(
                            modifier = Modifier
                                .background(
                                    color = Color(0xFF00ced1)
                                )
                                .height(80.dp),
                            verticalAlignment = Alignment.CenterVertically // 中央寄せ
                        ) {
                            Checkbox(
                                checked = todo.isCompleted,
                                onCheckedChange = { checked ->
                                    todo.isCompleted = checked
                                    todoViewModel.deleteTodo(todo.id)
                                }
                            )
                            TextButton(
                                onClick = {
                                    // 内容を編集する編集画面へ
                                    navController.navigate("edit/${todo.id}")
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = todo.title,
                                    style = TextStyle(
                                        fontSize = 30.sp
                                    ),
                                    color = Color(0xFF000000)

                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp)) // 16dpのスペース
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(
        navController = navController
    )

}
