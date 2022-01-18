package com.example.triviaapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable

class QuizActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val categoryName = intent.getStringExtra("categoryName")!!
//        savedInstanceState?.getBundle().getI = getIntent()
        setContent {
            Text(categoryName)
        }
    }
}
