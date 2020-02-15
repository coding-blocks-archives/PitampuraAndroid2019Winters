package com.puldroid.firebaseauth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun submit(view: View) {
        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
            .addOnCompleteListener {
                root.showSnack("Account Created Succesfully")
            }.addOnCanceledListener {

            }.addOnFailureListener {
                root.showSnack("Error : ${it.localizedMessage}")
            }


    }
}

fun View.showSnack(msg: String) {
    val snack = Snackbar.make(this, msg, Snackbar.LENGTH_LONG)
    snack.animationMode = Snackbar.ANIMATION_MODE_SLIDE
    snack.show()
}
