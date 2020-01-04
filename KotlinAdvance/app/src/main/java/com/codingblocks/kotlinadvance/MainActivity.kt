package com.codingblocks.kotlinadvance

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var text = "this is my first kotlin class"
        Log.i("RUN", text)
        run {
            var text = "this is my second kotlin class"
            Log.i("RUN", text)
        }
        Log.i("RUN", text)
        val txt: String? = "Pulkit"

        with(txt) {
            val size = this!!.length
            textView.text = txt
        }

        txt?.run {
            val size = length
            val value = this
            textView.text = txt
        }

        txt?.let {
            val size = it.length
            val value = it
            textView.text = txt
        }

        updateValue("Pulkit") { b: Boolean, s: String ->
            Log.i("LAMBDA", s + b)
        }

//        textView.setOnClickListener {
//
//        }
        textView.setOnClickListener(setonClickListener {
            val new = "Pulkit".convertToVowelOnly()
            Log.i("LAMBDA", "Custom On CLick $new")

        })
        textView.setOnLongDoubleClickListener{

        }

//        textView.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//
//
//            }
//        })
    }

    private fun setonClickListener(function: (it: Int) -> Unit): View.OnClickListener? {
        return object : View.OnClickListener {
            override fun onClick(v: View) {
                function(v.id)
            }
        }
    }

    private fun updateValue(a: String, function: (balue: Boolean, b: String) -> Unit) {
        when (a) {
            "Pulkit" -> function(true, a)
            "Archit" -> function(false, a)
            "Mehak" -> function(true, a)
            "Jatin" -> function(true, a)

        }

    }

}

fun String.convertToVowelOnly(): String {
    var new: String = ""
    for (i in this) {
        if (i == 'a' || i == 'e' || i == 'i' || i == 'o' || i == 'u') {
            new += i
        }
    }
    return new
}

fun View.setOnLongDoubleClickListener(function: (it: Int) -> Unit): View.OnClickListener? {
    return object : View.OnClickListener {
        override fun onClick(v: View) {
            function(v.id)
        }
    }
}
