package com.example.chatapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {

    val db by lazy {
        FirebaseDatabase.getInstance().reference
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        db.child("users").child("1").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                Log.i(
                    "DB", """
                   ${p0.child("count").value}
                   ${p0.child("isActive").value}
                   ${p0.child("name").value}
               """.trimIndent()
                )
            }

        })
//        db.child("users").addChildEventListener(object : ChildEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
//                Log.i(
//                    "DB", """
//
//                   ${p0.child("count").value}
//                   ${p0.child("isActive").value}
//                   ${p0.child("name").value}
//               """.trimIndent()
//                )            }
//
//            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
//                Log.i(
//                    "DB", """
//
//                   ${p0.child("count").value}
//                   ${p0.child("isActive").value}
//                   ${p0.child("name").value}
//               """.trimIndent()
//                )
//
//            }
//
//            override fun onChildRemoved(p0: DataSnapshot) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//        })
    }
}
