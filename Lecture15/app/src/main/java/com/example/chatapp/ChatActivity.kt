package com.example.chatapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    val db by lazy {
        FirebaseDatabase.getInstance().reference
    }
    val list = arrayListOf<Message>()
    val adapter = MessageAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        messageRv.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = this@ChatActivity.adapter
        }
        db.child("users").child("1").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
//                Log.i(
//                    "DB", """
//                   ${p0.child("count").value}
//                   ${p0.child("isActive").value}
//                   ${p0.child("name").value}
//               """.trimIndent()
//                )
            }

        })
        db.child("chat").addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val message = p0.getValue(Message::class.java)
                message?.let { list.add(it) }
                adapter.notifyDataSetChanged()
                messageRv.scrollToPosition(list.size-1)
                list.forEach {
                    Log.i(
                        "DB",
                        """
                    $it
                """.trimIndent()
                    )
                }

            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun sendMsg(view: View) {
        val msg = Message("Pulkit",msgEt.text.toString())
        db.child("chat").push().setValue(msg)
    }
}
