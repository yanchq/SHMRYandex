package com.example.shmryandex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.shmryandex.presentation.screens.expenses.ExpensesScreen
import com.example.shmryandex.presentation.screens.expenses.MainScreen
import com.example.shmryandex.ui.theme.SHMRYandexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SHMRYandexTheme {
                MainScreen()
            }
        }
    }
}
