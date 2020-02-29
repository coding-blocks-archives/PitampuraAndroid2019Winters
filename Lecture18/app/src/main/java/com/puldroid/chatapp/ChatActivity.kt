package com.puldroid.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.firebase.ui.database.FirebaseListAdapter
import com.firebase.ui.database.FirebaseListOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.item_chat.view.*

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
        button2.setOnClickListener {
            db.child("messages")
//            .child(uid)
                .push()
                .setValue(
                    Messages(
                        uid,
                        System.currentTimeMillis(),
                        input.text.toString(),
                        auth.currentUser?.email!!
                    )
                )
        }



        val query = db.child("messages")

        val options = FirebaseListOptions.Builder<Messages>()
            .setLayout(R.layout.item_chat)
            .setQuery(query, Messages::class.java)
            .build()

        val adapter = object : FirebaseListAdapter<Messages>(options) {
            override fun populateView(v: View, model: Messages, position: Int) {
                v.message.text = model.msg
                v.name.text = model.email
                v.time.text = model.time.toString()
            }

            override fun onDataChanged() {
                super.onDataChanged()
            }

        }
        adapter.startListening()

        messages.adapter = adapter

    }
}

/*
{
  "rules": {
    "messages": {
      ".write" : "auth != null",
    	".read" : "auth != null",
			"$msg" : {
        ".validate" : "newData.hasChildren(['email','from','time','msg'])",
          "msg" :{
            ".validate":"newData.isString()"
          },
           "time" :{
            ".validate":"newData.isNumber()"
          }
      }
    }
}
}
 */
