package com.e.jetpackcours.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.e.jetpackcours.R
import com.e.jetpackcours.ui.BaseActivity
import com.e.jetpackcours.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tool_bar.setOnClickListener {
            sessionManager.logout()
        }

        subscribeObservers()
    }


    private fun subscribeObservers(){
        sessionManager.cachedToken.observe(this , Observer {authToken->
            Log.d(TAG , "MainActivity : subscribeObservers : AuthToken $authToken")
            if(authToken == null || authToken.account_pk == -1 || authToken.token == null){
                navAuthActivity()
                finish()
            }
        })
    }

    private fun navAuthActivity() {
        val intent = Intent(this , AuthActivity::class.java)
        startActivity(intent)
        finish()

    }
}