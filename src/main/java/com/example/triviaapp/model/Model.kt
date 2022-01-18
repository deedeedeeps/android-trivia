package com.example.triviaapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.triviaapp.service.GetTriviaService
import com.example.triviaapp.network.RetroFitClientInstance
import com.example.triviaapp.dto.CategoryList
import com.example.triviaapp.dto.QuestionDTO
import com.example.triviaapp.dto.QuestionList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppState(
    private val service: GetTriviaService =
        RetroFitClientInstance.retrofitInstance?.create(GetTriviaService::class.java)!!,
) : ViewModel() {
    var categoryList by mutableStateOf<List<String>>(emptyList())
        private set
    var questionList by mutableStateOf<List<QuestionDTO>>(emptyList())
        private set
    var selectedCategory by mutableStateOf<String?>(null)
    val error = mutableStateOf<String?>(null)

    init {
        fetchCategories()
        fetchQuestions()
    }

    private fun fetchCategories() {
        selectedCategory = null
        val call = service.getAllCategories()
        call?.enqueue(object : Callback<CategoryList> {
            override fun onResponse(
                call: Call<CategoryList>,
                response: Response<CategoryList>,
            ) {
                val categoryNames = getCategoryNames(response)
                categoryList = categoryNames
            }
            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                error.value = "Error reading JSON"
            }
        })
    }

    private fun getCategoryNames(response: Response<CategoryList>): MutableList<String> {
        val body = response?.body()
        val categories = body?.categories
        val categoryNames = mutableListOf<String>()
        categories?.forEach { categoryNames.add(it.name) }
        return categoryNames
    }

    private fun fetchQuestions(){
        val call = service.getAllQuestionsForCategory(10, 27, "easy", "boolean")
        call?.enqueue(object  : Callback<QuestionList>{
            override fun onResponse(
                call: Call<QuestionList>,
                response: Response<QuestionList>,
            ) {
                val questionList = response.body()?.questions
            }
            override fun onFailure(call: Call<QuestionList>, t: Throwable){
                error.value = "Error reading JSON"
            }
        })
    }
}