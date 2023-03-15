package com.example.reciepiapp

import android.app.Application
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication:Application() {

    companion object {
        lateinit var retrofit: Retrofit
            private set
    }

    override fun onCreate() {
        super.onCreate()


        retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}