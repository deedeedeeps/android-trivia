package com.example.triviaapp.dto

import com.google.gson.annotations.SerializedName

class CategoryList(
    @SerializedName("trivia_categories")
    var categories : List<CategoryDTO>
) {
}