package com.example.reciepiapp.`interface`

import com.example.reciepiapp.models.RecipesInstruction
import com.example.reciepiapp.models.RecipesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesApi {

    @GET("/recipes/{id}/analyzedInstructions")
    fun getRecipeInstructions(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): Call<RecipesInstruction>

    @GET("/recipes/complexSearch")
    fun recipesList(
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String
    ): Call<RecipesList>

    @GET("/recipes/complexSearch")
    fun getSearchList(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String
    ): Call<RecipesList>





}