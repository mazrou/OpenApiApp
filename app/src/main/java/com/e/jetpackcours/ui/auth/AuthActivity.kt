package com.e.jetpackcours.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.e.jetpackcours.R
import com.e.jetpackcours.ui.BaseActivity
import com.e.jetpackcours.ui.ResponseType
import com.e.jetpackcours.ui.main.MainActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class AuthActivity : BaseActivity() , KodeinAware {

    override val kodein by closestKodein()

    val viewModel : AuthViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG , "AuthActivity : onCreate()  : ViewModel  ${viewModel.hashCode()}")
        setContentView(R.layout.activity_auth)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(this  , Observer {dataState ->
            dataState.data?.let {data ->
                data.data?.let{event ->
                    event.getContentIfNotHandled()?.let { it ->
                        it.authToken?.let {token->
                            Log.d(TAG , "AuthActivity  , subscribeObservers() : AuthViewState $token")
                            viewModel.setAuthToken(token)
                        }
                    }
                    data.response?.let {event ->
                        event.getContentIfNotHandled()?.let {
                            when (it.responseType){
                                is ResponseType.Dialog ->{
                                }
                                is ResponseType.Toast ->{

                                }
                                is ResponseType.None ->{
                                    Log.d(TAG , "AuthActivity : Response :${it.message}")
                                }
                            }
                        }
                    }
                }

            }
        })


        viewModel.viewState.observe(this, Observer{ viewState ->
            Log.d(TAG, "AuthActivity, subscribeObservers: AuthViewState: $viewState")
            viewState.authToken?.let{token->
                sessionManager.login(token)
            }
        })

        sessionManager.cachedToken.observe(this, Observer{ dataState ->
            Log.d(TAG, "AuthActivity, subscribeObservers: AuthDataState: $dataState")

            dataState.let{ authToken ->
                if(authToken != null && authToken.account_pk != -1 && authToken.token != null){
                    navMainActivity()
                }
            }
        })
    }

    private fun navMainActivity(){
        Log.d(TAG, "navMainActivity: called.")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
