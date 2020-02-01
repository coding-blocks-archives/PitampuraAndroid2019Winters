package com.puldroid.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val okHttpClient = OkHttpClient()

        val request = Request.Builder()
                .url("https://api.github.com/users/aggarwalpulkit596")
                .build()
        val gson = Gson()

        GlobalScope.launch(Dispatchers.Main) {
            val user = withContext(Dispatchers.IO) {
                val result = okHttpClient.newCall(request).execute()
                gson.fromJson<User>(result.body?.string(), User::class.java)
            }
            textView.text = user.name
            textView2.text = user.id.toString()
            textView3.text = user.bio
            Picasso.get().load(user.avatar_url).into(imageView)

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

