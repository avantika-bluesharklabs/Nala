package com.nala.view.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelContinueAs
import com.nala.businesslogic.viewmodel.activities.ViewModelForgotPassword
import com.nala.businesslogic.viewmodel.activities.ViewModelSignIn
import com.nala.databinding.ActivityContinueAsBinding
import com.nala.databinding.ActivitySignInBinding


class ActivityContinueAs : ActivityBase() {

    private lateinit var mViewModelContinueAs: ViewModelContinueAs
    private lateinit var mBinding: ActivityContinueAsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_continue_as)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_continue_as)
        mViewModelContinueAs = ViewModelContinueAs(mApplication, true)
        mBinding.vmContinueAs = mViewModelContinueAs

        observable()

    }

    fun observable() {

        mViewModelContinueAs.getLiveEventBackPress().observe(this, {

            onBackPressed()

        })

        mViewModelContinueAs.getLiveEvenTherapist().observe(this, {

            if (it) {

                startActivity(Intent(this, ActivitySignUp::class.java))

            }
        })

        mViewModelContinueAs.getLiveEventSignin().observe(this, {

            if (it) {

                startActivity(Intent(this, ActivitySignIn::class.java))

            }
        })

        mViewModelContinueAs.getLiveEvenClient().observe(this, {

            if (it) {

                startActivity(Intent(this, ActivitySignUp::class.java))

            }
        })


    }
}