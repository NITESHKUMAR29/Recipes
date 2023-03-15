package com.example.reciepiapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reciepiapp.models.RecipesList
import com.example.reciepiapp.repository.Repository

class MainViewModel(private val repository: Repository):ViewModel(){

    private val error=MutableLiveData<Throwable>()

    fun getRecipesList(totalNumber:Int,apiKey:String):LiveData<RecipesList>{
        val data=MutableLiveData<RecipesList>()
        repository.getRecipesList(totalNumber,apiKey,data,error)
        return data
    }

    fun getSearchList(query:String,apiKey:String):LiveData<RecipesList>{
        val data=MutableLiveData<RecipesList>()
        repository.getSearchList(query,apiKey,data,error)
        return data
    }

}