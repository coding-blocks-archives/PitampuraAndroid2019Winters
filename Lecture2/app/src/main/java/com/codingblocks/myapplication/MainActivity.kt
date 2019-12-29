package com.codingblocks.myapplication

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Lifecycle", "On Create Called")
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
