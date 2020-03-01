package com.puldroid.chatapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.database.FirebaseListAdapter
import com.firebase.ui.database.FirebaseListOptions
import com.google.android.material.chip.Chip
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.smartreply.FirebaseTextMessage
import com.google.firebase.ml.naturallanguage.smartreply.SmartReplySuggestion
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.item_chat.view.*

class ChatActivity : AppCompatActivity() {
    val db by lazy {
        FirebaseDatabase.getInstance().reference
    }
    val auth by lazy {
        FirebaseAuth.getInstance()
    }
    val key by lazy {
        intent.getStringExtra("KEY") ?: "messages"
    }
    val reply by lazy {
        FirebaseNaturalLanguage.getInstance().smartReply
    }

    val list = arrayListOf<FirebaseTextMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val uid = auth.currentUser?.uid!!
        button2.setOnClickListener {
            db.child(key)
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


        val query = db.child(key)

        val options = FirebaseListOptions.Builder<Messages>()
            .setLayout(R.layout.item_chat)
            .setQuery(query, Messages::class.java)
            .build()

        val adapter = object : FirebaseListAdapter<Messages>(options) {
            override fun populateView(v: View, model: Messages, position: Int) {
                v.message.text = model.msg
                v.name.text = model.email
                v.time.text = model.time.toString()
                if (model.from == uid) {
                    list.add(
                        (FirebaseTextMessage.createForLocalUser(
                            model.msg,
                            model.time
                        ))
                    )
                    v.setBackgroundColor(Color.RED)
                } else {
                    list.add(
                        (FirebaseTextMessage.createForRemoteUser(
                            model.msg,
                            model.time,
                            model.from
                        ))
                    )
                    v.setBackgroundColor(Color.WHITE)
                }
            }

            override fun onDataChanged() {
                super.onDataChanged()
                if (list.isNotEmpty())
                    reply.suggestReplies(
                        list.subList(
                            list.size - 2,
                            list.size
                        )
                    ).addOnSuccessListener {
                        if (it.suggestions.isNotEmpty()) {
                            if (chip_group.childCount > 0)
                                chip_group.removeAllViews()
                            it.suggestions.forEachIndexed { i: Int, it: SmartReplySuggestion ->
                                Log.i("Replies", "${it.text}")
                                val chip = Chip(this@ChatActivity)
                                chip.text = it.text
                                chip.id = i
                                chip.isClickable = true
                                chip.setOnClickListener {
                                    input.setText(chip.text)
                                    button2.callOnClick()
                                    chip_group.removeAllViews()
                                }
                                chip_group.addView(chip)


                            }
                        }
                    }
                messages.smoothScrollToPosition(count - 1)
            }

        }

//        chip_group.setOnCheckedChangeListener { group, checkedId ->
//            for (i in 0 until group.childCount) {
//                val chip:Chip = group.getChildAt(i) as Chip
//                if(chip.id == group.checkedChipId){
//                    input.setText(chip.text)
//                    button2.callOnClick()
//                }
//            }
//        }
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
