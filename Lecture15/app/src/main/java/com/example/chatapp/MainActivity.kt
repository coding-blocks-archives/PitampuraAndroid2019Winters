package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*

private val AUTH_EVENT_SUCCESS = 1
private val AUTH_EVENT_FAILURE = -1
private val AUTH_EVENT_EXISTS = 2


class MainActivity : AppCompatActivity() {

    private var mCallbackManager: CallbackManager? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    val mAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private var authEvent = MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(applicationContext)
        setContentView(R.layout.activity_main)

        setupFacebookRegister()
        startActivity(Intent(this,ChatActivity::class.java))

        authEvent.observe(this, Observer {
            if (it != null)
            {
            }
                when (it) {
                    AUTH_EVENT_SUCCESS -> {

                    }
                    AUTH_EVENT_EXISTS -> {

                    }
                    AUTH_EVENT_FAILURE ->{

                    }
                }
        })

    }

    private fun setupFacebookRegister() {
        mCallbackManager = CallbackManager.Factory.create()
        facebookLoginButton.setPermissions("email", "public_profile")
        facebookLoginButton
            .registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    registerFacebookUser(loginResult.accessToken)
                }

                override fun onCancel() {
                }

                override fun onError(error: FacebookException) {
                }
            })
    }

    private fun registerFacebookUser(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (task.result?.additionalUserInfo?.isNewUser!!) {
                    authEvent.postValue(AUTH_EVENT_SUCCESS)
                } else {
                    authEvent.postValue(AUTH_EVENT_EXISTS)
                }
            } else {
                authEvent.postValue(AUTH_EVENT_FAILURE)
            }
        }.addOnFailureListener { e ->
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCallbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE && resultCode == RESULT_OK) {
            registerGoogleUser(GoogleSignIn.getSignedInAccountFromIntent(data))
        }
    }

    private fun registerGoogleUser(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.result
            val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
            mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (task.result?.additionalUserInfo?.isNewUser!!) {
                        authEvent.postValue(AUTH_EVENT_SUCCESS)
                    } else {
                        authEvent.postValue(AUTH_EVENT_EXISTS)
                    }
                } else {
                    authEvent.postValue(AUTH_EVENT_FAILURE)
                }
            }
        } catch (e: ApiException) {
            e.printStackTrace()
        }
    }

    fun onClickGoogleLogin(v: View) {
        mGoogleSignInClient = GoogleSignIn.getClient(this, getSignInOptions()!!)
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE)
    }

    private fun getSignInOptions(): GoogleSignInOptions? {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

    }

    fun onClickFacebookLogin(view: View) {
        facebookLoginButton.performClick()
    }

    companion object {
        private const val GOOGLE_SIGN_IN_REQUEST_CODE = 1
    }
}
