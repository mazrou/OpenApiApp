package com.e.jetpackcours.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.e.jetpackcours.R
import com.e.jetpackcours.activityScopedFragmentViewModel
import kotlinx.android.synthetic.main.fragment_launcher.*

class LauncherFragment
    : BaseAuthFragment(
    R.layout.fragment_launcher
) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        Log.d(TAG , "LauncherFragment : ${viewModel.hashCode()}")


        register.setOnClickListener{
            navigationRegistration()
        }

        login.setOnClickListener {
            navLogin()
        }

        forgot_password.setOnClickListener {
            navForgotPassword()
        }

        focusable_view.requestFocus()
    }

    private fun navForgotPassword() {
        findNavController().navigate(R.id.action_launcherFragment_to_forgotPasswordFragment)
    }

    private fun navLogin() {
        findNavController().navigate(R.id.action_launcherFragment_to_loginFragment)
    }

    private fun navigationRegistration() {
        findNavController().navigate(R.id.action_launcherFragment_to_registerFragment)

    }


}