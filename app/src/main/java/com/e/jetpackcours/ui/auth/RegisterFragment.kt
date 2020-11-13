package com.e.jetpackcours.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.e.jetpackcours.R
import com.e.jetpackcours.activityScopedFragmentViewModel
import com.e.jetpackcours.ui.auth.state.AuthStateEvent
import com.e.jetpackcours.ui.auth.state.RegistrationFields
import com.e.jetpackcours.util.GenericApiResponse
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseAuthFragment(R.layout.fragment_register) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG , "RegisterFragment : ${viewModel.hashCode()}")

        register_button.setOnClickListener {
            register()
        }

        subscribeObservers()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setRegistrationFields(
            RegistrationFields(
                registration_email = input_email.text.toString() ,
                registration_username = input_username.text.toString(),
                registration_password = input_password.text.toString() ,
                registration_confirm_password = input_password_confirm.text.toString()
            )
        )
    }

    private fun register(){
        viewModel.setStateEvent(
            AuthStateEvent.RegisterAttemptEvent(
                email= input_email.text.toString() ,
                username = input_username.text.toString(),
                password = input_password.text.toString() ,
                confirm_password = input_password_confirm.text.toString()
            )
        )
    }
    private fun subscribeObservers(){
        viewModel.viewState.observe(viewLifecycleOwner , Observer {
            it.registrationFields?.let {registrationFields->
                registrationFields.registration_email?.let {text -> input_email.setText(text) }
                registrationFields.registration_username?.let {text -> input_username.setText(text) }
                registrationFields.registration_password?.let {text -> input_password.setText(text) }
                registrationFields.registration_password?.let {text -> input_password_confirm.setText(text) }
            }
        })

    }
}