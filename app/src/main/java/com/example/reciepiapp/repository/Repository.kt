package com.example.reciepiapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.reciepiapp.`interface`.RecipesApi
import com.example.reciepiapp.models.RecipesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val recipesApi: RecipesApi) {

    fun getRecipesList(
        totalNumber: Int,
        apiKey: String,
        data: MutableLiveData<RecipesList>,
        error: MutableLiveData<Throwable>
    ) {
        recipesApi.recipesList(totalNumber,apiKey).enqueue(object : Callback<RecipesList?> {
            override fun onResponse(call: Call<RecipesList?>, response: Response<RecipesList?>) {
                if (response.body()!=null){
                    data.value=response.body()
                }
                else{
                   data.value=null
                }

            }

            override fun onFailure(call: Call<RecipesList?>, t: Throwable) {
               error.value=t
            }
        })
    }

    fun getSearchList(
        query: String,
        apiKey: String,
        data: MutableLiveData<RecipesList>,
        error: MutableLiveData<Throwable>
    ) {
        recipesApi.getSearchList(query,apiKey).enqueue(object : Callback<RecipesList?> {
            override fun onResponse(call: Call<RecipesList?>, response: Response<RecipesList?>) {
                if (response.body()!=null){
                    data.value=response.body()
                    Log.d("dataaa",response.body().toString())
                }
                else{
                    data.value=null
                }

            }

            override fun onFailure(call: Call<RecipesList?>, t: Throwable) {
                error.value=t
                Log.d("dataaa",t.message.toString())
            }
        })
    }
}