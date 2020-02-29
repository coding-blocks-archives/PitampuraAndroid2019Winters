package com.puldroid.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ChatActivity : AppCompatActivity() {
    val db by lazy {
        FirebaseDatabase.getInstance().reference
    }
    val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val uid = auth.currentUser?.uid!!
        db.child("messages")
//            .child(uid)
            .push()
            .setValue(
                Messages(
                    uid,
                    System.currentTimeMillis(),
                    "Hello")
            )
    }
}
