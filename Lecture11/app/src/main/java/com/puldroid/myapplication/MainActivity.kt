package com.puldroid.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val okHttpClient = OkHttpClient()

        val request = Request.Builder()
                .url("https://api.github.com/users/aggarwalpulkit596")
                .build()
        GlobalScope.launch(Dispatchers.IO) {
            val result = okHttpClient.newCall(request).execute()
            Log.i("Execute Function", result.toString())
            if (result.isSuccessful) {
                result.body?.let {
                    val gson = Gson()
                    val user = gson.fromJson<User>(it.string(),User::class.java)
//                    val json = JSONObject(it.string())
//                    val name = json.getString("name")
//                    val id = json.getLong("id")
//                    val avatar_url = json
//                            .getString("avatar_url")
//                    val bio:String = json.getString("bio")
//                    val login = json.getString("login")
//                    val user = USer(name,id,avatar_url,bio,login)
                    Log.i("Execute Function", "$user")
                } ?: run {
                    Log.i("Execute Function", "error")
                }
            }
        }


    }

}
