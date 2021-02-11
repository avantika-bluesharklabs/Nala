package com.nala.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelSignUp
import com.nala.databinding.ActivitySignUpBinding
import com.nala.utils.ConstantCodes
import com.nala.utils.Utils
import java.util.*


class ActivitySignUp : ActivityBase() {

    private lateinit var mViewModelSignUp: ViewModelSignUp
    private lateinit var mBinding: ActivitySignUpBinding
    private lateinit var callbackManager: CallbackManager
    lateinit var mGoogleSignInClient: GoogleSignInClient

    private val EMAIL = "email"


    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        mViewModelSignUp = ViewModelSignUp(mApplication, true)
        mBinding.vmSignUp = mViewModelSignUp

        callbackManager = CallbackManager.Factory.create()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("YOUR_WEB_APPLICATION_CLIENT_ID")
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        mBinding.btnGoogle.setOnClickListener {

            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, ConstantCodes.RC_SIGN_IN)

        }

        mBinding.btnFacebook.setOnClickListener {

            // mBinding.btnFacebook.setRead(Arrays.asList(EMAIL));

            LoginManager.getInstance()
                .logInWithReadPermissions(this, Arrays.asList("public_profile"));


            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult?> {
                    override fun onSuccess(loginResult: LoginResult?) {
                        // App code
                    }

                    override fun onCancel() {
                        // App code
                    }

                    override fun onError(exception: FacebookException) {
                        // App code
                    }
                })

            val accessToken = AccessToken.getCurrentAccessToken()
            accessToken != null && !accessToken.isExpired

        }

        mBinding.imgBack.setOnClickListener { finish() }

        observable()


    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun observable() {


        mViewModelSignUp.getLiveEventBackPress().observe(this, {
            onBackPressed()
        })


        mViewModelSignUp.getLiveEventUser().observe(this, {

        })



        mViewModelSignUp.getLiveEventSignUp().observe(this, {

            Utils.hideKeyboard(this@ActivitySignUp)

            if (it) {
                startActivity(Intent(this, ActivityPhoneVerification::class.java))
                finish()

//                mViewModelSignUp.networkCallData()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ConstantCodes.RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                ApiException::class.java
            )
            // Signed in successfully
            val googleId = account?.id ?: ""
            Log.i("Google ID", googleId)

            val googleFirstName = account?.givenName ?: ""
            Log.i("Google First Name", googleFirstName)

            val googleLastName = account?.familyName ?: ""
            Log.i("Google Last Name", googleLastName)

            val googleEmail = account?.email ?: ""
            Log.i("Google Email", googleEmail)

            val googleProfilePicURL = account?.photoUrl.toString()
            Log.i("Google Profile Pic URL", googleProfilePicURL)

            val googleIdToken = account?.idToken ?: ""
            Log.i("Google ID Token", googleIdToken)

        } catch (e: ApiException) {
            // Sign in was unsuccessful
            Log.e(
                "failed code=", e.statusCode.toString()
            )
        }
    }


}