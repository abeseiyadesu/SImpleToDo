package com.example.simpletodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.simpletodoapp.ui.TodoApp
import com.example.simpletodoapp.ui.theme.SimpleTodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleTodoAppTheme {
                TodoApp()
            }
        }
    }
}

@Preview
@Composable
fun SimpleTodoPreview(){
    TodoApp()
}


