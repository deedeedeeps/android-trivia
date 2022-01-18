package com.example.triviaapp.dto

import com.google.gson.annotations.SerializedName

data class QuestionDTO(
    var category: String,
    var type: String,
    var difficulty: String,
    var question: String,
    var correct_answer: String,
    var incorrect_answers: List<String>
)