package com.e.jetpackcours.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.e.jetpackcours.R
import com.e.jetpackcours.activityScopedFragmentViewModel
import com.e.jetpackcours.data.model.AuthToken
import com.e.jetpackcours.ui.auth.state.AuthStateEvent
import com.e.jetpackcours.ui.auth.state.LoginFields
import com.e.jetpackcours.util.GenericApiResponse
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseAuthFragment(R.layout.fragment_login) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG , "LoginFragment : ${viewModel.hashCode()}")

        subscribeObservers()

        login_button.setOnClickListener {
            login()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setLoginFields(
            LoginFields(
                login_email = input_email.text.toString() ,
                login_password = input_password.text.toString()
            )
        )
    }

    private fun login (){
        viewModel.setStateEvent(
            AuthStateEvent.LoginAttemptEvent(
                input_email.text.toString() ,
                input_password.text.toString()
            )
        )

    }
    private fun subscribeObservers(){
        viewModel.viewState.observe(viewLifecycleOwner , Observer {
            it.loginFields?.let {loginFiewlds->
                loginFiewlds.login_email?.let {text -> input_email.setText(text) }
                loginFiewlds.login_password?.let {text -> input_password.setText(text) }
            }
        })
    }


}