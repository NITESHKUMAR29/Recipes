package com.example.reciepiapp.models

data class Recipes(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)