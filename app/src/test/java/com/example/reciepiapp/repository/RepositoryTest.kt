package com.example.reciepiapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.reciepiapp.`interface`.RecipesApi
import com.example.reciepiapp.models.RecipesList
import com.example.reciepiapp.models.Results
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRecipesApi: RecipesApi

    private lateinit var repository: Repository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = Repository(mockRecipesApi)
    }

    @Test
    fun testGetRecipesListSuccess() {
        val mockRecipesList = RecipesList(1, 0, listOf(Results(1, "", "", "Title")), 1)
        val mockCall = Mockito.mock(Call::class.java) as Call<RecipesList>
        Mockito.`when`(mockRecipesApi.recipesList(1, "apiKey")).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.arguments[0] as Callback<RecipesList>
            callback.onResponse(mockCall, Response.success(mockRecipesList))
        }.`when`(mockCall).enqueue(Mockito.any())

        val data = MutableLiveData<RecipesList>()
        val error = MutableLiveData<Throwable>()
        repository.getRecipesList(1, "apiKey", data, error)

        assertNotNull(data.value)
        assertEquals(mockRecipesList, data.value)
        assertEquals(null, error.value)
    }

    @Test
    fun testGetRecipesListFailure() {
        val mockCall = Mockito.mock(Call::class.java) as Call<RecipesList>
        Mockito.`when`(mockRecipesApi.recipesList(1, "apiKey")).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.arguments[0] as Callback<RecipesList>
            callback.onFailure(mockCall, Throwable("Network Error"))
        }.`when`(mockCall).enqueue(Mockito.any())

        val data = MutableLiveData<RecipesList>()
        val error = MutableLiveData<Throwable>()
        repository.getRecipesList(1, "apiKey", data, error)

        assertEquals(null, data.value)
        assertNotNull(error.value)
    }

}



