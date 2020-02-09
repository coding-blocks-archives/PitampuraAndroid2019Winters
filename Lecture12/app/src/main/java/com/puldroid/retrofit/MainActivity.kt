package com.puldroid.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){Client.api.getAllUsers()}
            if(response.isSuccessful){
                response.body()?.let{
                    Log.i("Retrofit","$it")
                }
            }
        }
    }
}
