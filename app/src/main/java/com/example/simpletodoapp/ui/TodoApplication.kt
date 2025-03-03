package com.example.simpletodoapp.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.simpletodoapp.ui.home.HomeScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simpletodoapp.ui.add.AdditionalScreen
import com.example.simpletodoapp.ui.edit.EditScreen

@Composable
fun TodoApp() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        // 画面遷移用コントローラー
        val navController = rememberNavController()

        // 画面遷移部分
        NavHost(navController = navController, startDestination = "home") {
            //　ホーム画面へ遷移
            composable("home") {
                HomeScreen(navController = navController)
            }
            // タスク追加画面へ遷移
            composable("add") {
                AdditionalScreen(
                    navController = navController,
                    onBackClick = { navController.popBackStack() } // ラムダを期待するため、{}で渡す Unit
                )
            }

            // タスクの
            composable(
                route = "edit/{todoId}",
                // Idは　Int型のため  Int  に変換する必要がある
                arguments = listOf(
                    androidx.navigation.navArgument("todoId") {
                        type = androidx.navigation.NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val todoId = backStackEntry.arguments?.getInt("todoId")
                // nullチェック
                if (todoId != null) {
                    EditScreen(
                        navController = navController,
                        onBackClick = { navController.popBackStack() },
                        todoId = todoId
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TodoAppPreview() {
    TodoApp()
}