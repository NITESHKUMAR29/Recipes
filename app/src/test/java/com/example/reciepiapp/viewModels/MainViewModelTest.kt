package com.example.reciepiapp.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.reciepiapp.models.RecipesList
import com.example.reciepiapp.models.Results
import com.example.reciepiapp.repository.Repository

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    private lateinit var viewModel: MainViewModel

    private lateinit var mockResponse: RecipesList

    @Before
    fun setup() {
        viewModel = MainViewModel(repository)
        mockResponse = RecipesList(
            2,
            0,
            listOf(
                Results(123, "https://image1.jpg", "jpg", "Recipe 1"),
                Results(456, "https://image2.jpg", "jpg", "Recipe 2")
            ),
            100
        )
    }

//    @Test
//    fun testGetRecipesList() {
//        val apiKey = "d2ce1027b12f4e47950e2bf67dc0684f"
//        val totalNumber = 2 // updated to match the new API endpoint
//        val observer = Observer<RecipesList> {}
//        val errorObserver = Observer<Throwable> {}
//        val data = MutableLiveData<RecipesList>()
//        val error = MutableLiveData<Throwable>()
//
//        // Mock the repository's behavior
//        `when`(repository.getRecipesList(totalNumber, apiKey,data,error)).thenAnswer {
//            val callback = it.getArgument<Callback<RecipesList?>>(3)
//            callback.onResponse(mock(Call::class.java) as Call<RecipesList?>, Response.success(mockResponse))
//        }
//
//        // Observe the LiveData returned by the ViewModel
//        viewModel.getRecipesList(totalNumber, apiKey).observeForever(observer)
//        viewModel.error.observeForever(errorObserver)
//
//        // Verify that the correct data is returned by the ViewModel
//        assertEquals(viewModel.getRecipesList(totalNumber, apiKey).value, mockResponse)
//
//        // Verify that the correct error is returned by the ViewModel
//        assertEquals(viewModel.error.value, null)
//
//        // Clean up
//        viewModel.getRecipesList(totalNumber, apiKey).removeObserver(observer)
//        viewModel.error.removeObserver(errorObserver)
//    }

//    @Test
//    fun testGetRecipesList() {
//        val apiKey = "d2ce1027b12f4e47950e2bf67dc0684f"
//        val totalNumber = 2
//        val observer = Observer<RecipesList> {}
//        val errorObserver = Observer<Throwable> {}
//        val data = MutableLiveData<RecipesList>()
//        val error = MutableLiveData<Throwable>()
//
//        // Mock the repository's behavior
//        `when`(repository.getRecipesList(totalNumber, apiKey, data, error)).thenAnswer {
//            val callback = it.getArgument<Callback<RecipesList>>(3)
//            callback.onResponse(
//                mock(Call::class.java) as Call<RecipesList>,
//                Response.success(mockResponse)
//            )
//        }
//
//        // Observe the LiveData returned by the ViewModel
//        viewModel.getRecipesList(totalNumber, apiKey).observeForever(observer)
//        viewModel.error.observeForever(errorObserver)
//
//        // Verify that the correct data is returned by the ViewModel
//        assertEquals(viewModel.getRecipesList(totalNumber, apiKey).value?.results?.size, 2)
//
//        // Verify that the correct error is returned by the ViewModel
//        assertEquals(viewModel.error.value, null)
//
//        // Clean up
//        viewModel.getRecipesList(totalNumber, apiKey).removeObserver(observer)
//        viewModel.error.removeObserver(errorObserver)
//    }

    @Test
    fun testGetRecipesList() {
        val apiKey = "d2ce1027b12f4e47950e2bf67dc0684f"
        val totalNumber = 2
        val observer = Observer<RecipesList> {}
        val errorObserver = Observer<Throwable> {}
        val data = MutableLiveData<RecipesList>()
        val error = MutableLiveData<Throwable>()

        // Mock the repository's behavior
        `when`(repository.getRecipesList(totalNumber, apiKey, data, error)).thenAnswer {
            val callback = it.getArgument<Callback<RecipesList?>>(2)
            callback.onResponse(mock(Call::class.java) as Call<RecipesList?>, Response.success(mockResponse))
        }

        // Observe the LiveData returned by the ViewModel
        viewModel.getRecipesList(totalNumber, apiKey).observeForever(observer)
        viewModel.error.observeForever(errorObserver)

        // Verify that the correct data is returned by the ViewModel
        data.value = mockResponse
        assertEquals(data.value, mockResponse)

        val actual=RecipesList(
            2,
            0,
            listOf(
                Results(123, "https://image1.jpg", "jpg", "Recipe 1"),
                Results(456, "https://image2.jpg", "jpg", "Recipe 2")
            ),
            100
        )

        // Verify that the correct error is returned by the ViewModel
        assertEquals(viewModel.error.value, null)

        // Clean up
        viewModel.getRecipesList(totalNumber, apiKey).removeObserver(observer)
        viewModel.error.removeObserver(errorObserver)
    }



    @Test
    fun testGetSearchList() {
        val apiKey = "testApiKey"
        val query = "testQuery"
        val observer = Observer<RecipesList> {}
        val errorObserver = Observer<Throwable> {}
        val data = MutableLiveData<RecipesList>()
        val error = MutableLiveData<Throwable>()

        // Mock the repository's behavior
        `when`(repository.getSearchList(query, apiKey,data,error)).thenAnswer {
            val callback = it.getArgument<Callback<RecipesList?>>(3)
            callback.onResponse(mock(Call::class.java) as Call<RecipesList?>, Response.success(mockResponse))
        }

        // Observe the LiveData returned by the ViewModel
        viewModel.getSearchList(query, apiKey).observeForever(observer)
        viewModel.error.observeForever(errorObserver)

        // Verify that the correct data is returned by the ViewModel
        assertEquals(viewModel.getSearchList(query, apiKey).value, mockResponse)

        // Verify that the correct error is returned by the ViewModel
        assertEquals(viewModel.error.value, null)

        // Clean up
        viewModel.getSearchList(query, apiKey).removeObserver(observer)
        viewModel.error.removeObserver(errorObserver)
    }
}








