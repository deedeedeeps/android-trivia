package com.example.triviaapp.service

import com.example.triviaapp.dto.CategoryList
import com.example.triviaapp.dto.QuestionList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetTriviaService {

    @GET("api_category.php")
    fun getAllCategories(): Call<CategoryList>

    @GET("api.php")
    fun getAllQuestionsForCategory(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String,
    ): Call<QuestionList>
}