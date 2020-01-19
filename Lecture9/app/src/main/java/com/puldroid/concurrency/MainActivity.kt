package com.puldroid.concurrency

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main){
                val dialog = AlertDialog.Builder(this@MainActivity)
                        .setTitle("Pulkit")
                        .setMessage("Show Dialog")
                        .setCancelable(false)
                        .show()
                val b = GlobalScope.async(Dispatchers.Default) {showSomething()}.await()
                Log.i("Coroutines 2", "This is output $b")
                val a = withContext(Dispatchers.Default) {showSomething()}
                Log.i("Coroutines 2", "This is output $a")
                if(a && b){
                    dialog.dismiss()
                }

            }


            GlobalScope.launch(Dispatchers.Main) {
                Log.i("Coroutines 2", "This is first log")
            }
        }


//        thread(true) {
//            Log.i("Threads", "Thread 2")
//            printNumber("2")
//        }
//
//        thread(true) {
//            Log.i("Threads", "Thread 3")
//            printNumber("3")
//        }
    }

    private suspend fun showSomething(): Boolean {
        Log.i("Coroutines", "This is first log")
        delay(5000)
        Log.i("Coroutines", "This is second log")
        return true
    }

    private fun showSomethingThread() {
        Log.i("Coroutines", "This is first log")
        Thread.sleep(5000)
        Log.i("Coroutines", "This is second log")
    }

    private fun printNumber(name: String) {
        for (i in 0..100000) {
            if (i == 5000) {
                textView.text = "5000"
            }
            Log.i("Thread $name", "$i")
        }
    }
}

