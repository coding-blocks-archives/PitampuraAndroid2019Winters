package com.puldroid.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val list = arrayListOf<User>()
    val adapter = UserAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        adapter.onItemClick = {
            Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
        }
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllUsers() }
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    list.addAll(res)
                    runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            val response2 = withContext(Dispatchers.IO) { Client.api.getMe() }
            if (response2.isSuccessful) {
                response2.body()?.let { res ->
                    list.add(res)
                    runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}
