package com.example.reciepiapp

import android.app.DownloadManager.Query
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reciepiapp.adapter.MainAdapter
import com.example.reciepiapp.contant.Constant
import com.example.reciepiapp.databinding.ActivityMainBinding
import com.example.reciepiapp.`interface`.RecipesApi
import com.example.reciepiapp.models.Results
import com.example.reciepiapp.repository.Repository
import com.example.reciepiapp.viewModels.MainViewModel
import com.example.reciepiapp.viewModels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        val service = MyApplication.retrofit.create(RecipesApi::class.java)
        val repository=Repository(service)
        viewModel=ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]

        buildMainRecycler()
        binding.search.setOnClickListener {
            if (binding.editText.text.isNullOrEmpty()){
                buildMainRecycler()
            }
            else{
                buildSearchRecycler((binding.editText.text.toString()))
            }
        }
    }
    private fun buildMainRecycler(){
        viewModel.getRecipesList(100,Constant.API_KEY).observe(this){

           var list=ArrayList<Results>()
            list= it.results as ArrayList<Results>

               val adapter=MainAdapter(this,list)
            binding.recyclerview.adapter=adapter
            binding.recyclerview.layoutManager=LinearLayoutManager(this)
        }
    }
    private fun buildSearchRecycler(query:String){
        Log.d("dataaa","enter1")
        viewModel.getSearchList(query,Constant.API_KEY).observe(this){
            Log.d("dataaa",it.toString())

            val list: ArrayList<Results> = it.results as ArrayList<Results>

            val adapter=MainAdapter(this,list)
            binding.recyclerview.adapter=adapter
            binding.recyclerview.layoutManager=LinearLayoutManager(this)
        }
    }
}