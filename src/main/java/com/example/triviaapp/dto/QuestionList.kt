package com.example.triviaapp.dto

import com.google.gson.annotations.SerializedName

class QuestionList(
    @SerializedName("trivia_categories")
    var questions : List<QuestionDTO>
) {
}