package com.puldroid.sharedprefs

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

const val prefs = "AppPrefs"

class MainActivity : AppCompatActivity() {

    var counter: Int = 0
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences(prefs, Context.MODE_PRIVATE)

        counter = sharedPreferences.getInt("COUNTER", 0)
        name = sharedPreferences.getString("NAME", "") ?: ""
        textView.text = counter.toString()
        editText.setText(name)
        button.setOnClickListener {
            sharedPreferences.edit().putString("NAME", editText.text.toString()).apply()
            textView.text = counter.toString()
            sharedPreferences.edit().putInt("COUNTER", ++counter).apply()
            Snackbar.make(root, "This is a Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("Retry") {
                }.setActionTextColor(getColor(R.color.colorAccent))
                .show()

            //Extension function
            root.showSnackbar("This is a Kotlin Snackbar")
        }
    }
}

fun View.showSnackbar(
    text: String,
    length: Int = Snackbar.LENGTH_SHORT,
    actionText: String = "",
    function: () -> Unit = {}
) {
    val snackbar = Snackbar.make(this, text, length)
    if (actionText.isNotEmpty()) {
        snackbar.setAction("Retry") {
            function()
        }
    } else {
        snackbar.show()
    }


}
