package com.example.triviaapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.triviaapp.model.AppState
import com.example.triviaapp.ui.theme.TriviaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            val appState = AppState()
            val appState: AppState by viewModels()
//            var selected =  appState.selectedCategory
//            var expanded by remember { mutableStateOf(false) }
            screen(appState)
            Button(appState)
        }
    }
}

@Composable
fun screen(vm: AppState = AppState()) // rename appstate = viewmodel
{
    var selected =  vm.selectedCategory
    var expanded by remember { mutableStateOf(false) }
    TriviaAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background) {
            Column() {
                Greeting("Trivia")
                CategorySelect(
                    categoryList = vm.categoryList,
                    selectedCategory = vm.selectedCategory,
                    expanded = expanded,
                    onExpanded = { expanded = it },
                    vm = vm
                )
                Text(selected ?: "not selected")
            }
        }
    }
}

@Composable
fun Button(appState: AppState) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
//                onClick = { Toast.makeText(context, "SELECT A CATEGORY", Toast.LENGTH_SHORT).show() },
                onClick = {context.startActivity(Intent(context, QuizActivity::class.java).apply{
                    putExtra("categoryName", appState.selectedCategory)
                })},
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Red
                )
            ) {
                Text("Start")
            }

        }
    }
}

@Composable
fun CategorySelect(
    categoryList: List<String>,
    selectedCategory: String?,
    expanded: Boolean = false,
    onExpanded: (Boolean) -> Unit,
    vm: AppState
) {
    // State variables
    var selectedCategory: String? by remember { mutableStateOf(null) }
    var expanded by remember { mutableStateOf(false) }
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        if (categoryList.isNotEmpty()) {
            Row(
                Modifier
                    .padding(24.dp)
                    .clickable {
                        expanded = !expanded
                    }
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) { // Anchor view
                Text(text = selectedCategory ?: "Pick a category",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 8.dp))
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")

                //
                DropdownMenu(expanded = expanded, onDismissRequest = {
                    expanded = false
                }) {
                    categoryList.forEach { cat ->
                        DropdownMenuItem(onClick = {
                            expanded = false
//                            selectedCategory = cat
                            vm.selectedCategory = cat
                        }) {
                            Text(text = cat)
                        }
                    }
                }
            }
        } else {
            Text("Loading")
        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "$name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TriviaAppTheme {
        Greeting("Android")
    }
}