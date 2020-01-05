package com.codingblocks.intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

const val NAME = "name"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val s = editText.text.toString()

            if (s.isEmpty()) {
                Toast.makeText(this,"Cannot Be Empty", Toast.LENGTH_LONG).show()
            } else {
                val i = Intent(this, Main2Activity::class.java)
                i.putExtra(NAME,s)
                startActivity(i)
            }
        }

        button2.setOnClickListener {
            val i = Intent(this, Main2Activity::class.java)
            startActivity(i)
        }

        button3.setOnClickListener {
        val i  = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${editText.text}"))
            startActivity(i)
        }

        button4.setOnClickListener {
            val i  = Intent(Intent.ACTION_VIEW, Uri.parse("http://${editText.text}"))
            startActivity(Intent.createChooser(i,"Select Browser"))
        }
        button5.setOnClickListener {
            val i  = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${editText.text}"))
            i.putExtra(Intent.EXTRA_SUBJECT,"Sample Subjevty")
            i.putExtra(Intent.EXTRA_TEXT,"Sample wojbdkjdbqwkjdb")
            startActivity(Intent.createChooser(i,"Select Browser"))
        }
    }
}
