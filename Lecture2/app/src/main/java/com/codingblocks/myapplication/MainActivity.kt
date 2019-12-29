package com.codingblocks.myapplication

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    val btn1 = findViewById<Button>(R.id.btn1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Linking of Activity class and XML(Layout)
        setContentView(R.layout.activity_main)
        Log.i("Lifecycle", "On Create Called")
        btn1.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(this@MainActivity,"Hello",Toast.LENGTH_SHORT).show()
            }

        })

////        a("p",5,age2 = 5,msg2 =  "Pulkit")
////        a(msg2 =  5,age2 = "p",5,"pulkit")
//        clickMe.setOnClickListener {
//            AlertDialog.Builder(this).setPositiveButton("Ok") { dialogInterface: DialogInterface, i: Int ->
//                dialogInterface.dismiss()
//            }.show()
//        }

    }

//    fun a(msg:String,age:Int,age2:Int,msg2:String){
//
//    }

    override fun onStart() {
        super.onStart()
        Log.i("Lifecycle", "On Start Called")

    }

    override fun onResume() {
        super.onResume()
        Log.i("Lifecycle", "On Resume Called")

    }

    override fun onPause() {
        super.onPause()
        Log.i("Lifecycle", "On Pause Called")

    }

    override fun onStop() {
        super.onStop()
        Log.i("Lifecycle", "On Stop Called")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Lifecycle", "On Destroy Called")

    }
}
