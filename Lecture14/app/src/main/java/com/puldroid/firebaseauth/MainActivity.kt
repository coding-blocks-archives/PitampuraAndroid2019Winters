package com.puldroid.firebaseauth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val auth by lazy { FirebaseAuth.getInstance() }
    lateinit var storedVerificationId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun submit(view: View) {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                if (e is FirebaseAuthInvalidCredentialsException) {
                    root.showSnack("${e.localizedMessage}")
                } else if (e is FirebaseTooManyRequestsException) {
                    root.showSnack("${e.localizedMessage}")
                }

            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                storedVerificationId = verificationId
                var resendToken = token
            }
        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91" + email.text.toString(), // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this, // Activity (for callback binding)
            callbacks
        ) // OnVerificationStateChangedCallbacks


    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                Log.d("TAG", "signInWithCredential:success")
                root.showSnack("Account Created Succesfully")

            }
            .addOnFailureListener {
                root.showSnack("Error : ${it.localizedMessage}")
            }
    }

    private fun signUpWithEmail() {
        auth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
            .addOnCompleteListener {
                root.showSnack("Account Created Succesfully")
            }.addOnCanceledListener {

            }.addOnFailureListener {
                root.showSnack("Error : ${it.localizedMessage}")
            }
    }

    fun verify(view: View) {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId, pass.text.toString())
        signInWithPhoneAuthCredential(credential)
    }
}

fun View.showSnack(msg: String) {
    val snack = Snackbar.make(this, msg, Snackbar.LENGTH_LONG)
    snack.animationMode = Snackbar.ANIMATION_MODE_SLIDE
    snack.show()
}
