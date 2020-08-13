package com.e.jetpackcours.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.e.jetpackcours.data.model.AuthToken
import com.e.jetpackcours.data.network.auth.network_responses.LoginResponse
import com.e.jetpackcours.data.network.auth.network_responses.RegistrationResponse
import com.e.jetpackcours.data.repository.auth.AuthRepository
import com.e.jetpackcours.ui.BaseViewModel
import com.e.jetpackcours.ui.DataState
import com.e.jetpackcours.ui.auth.state.AuthStateEvent
import com.e.jetpackcours.ui.auth.state.AuthStateEvent.*
import com.e.jetpackcours.ui.auth.state.AuthViewState
import com.e.jetpackcours.ui.auth.state.LoginFields
import com.e.jetpackcours.ui.auth.state.RegistrationFields
import com.e.jetpackcours.util.AbsentLiveData
import com.e.jetpackcours.util.GenericApiResponse

class AuthViewModel
constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<AuthStateEvent , AuthViewState>(){




    fun setRegistrationFields(registrationFields: RegistrationFields){
        val update = getCurrentViewStateOrNew()
        if(update.registrationFields == registrationFields) {
            return
        }
        update.registrationFields = registrationFields
        _viewState.postValue(update)
    }


    fun setLoginFields(loginFields: LoginFields){
        val update = getCurrentViewStateOrNew()
        if(update.loginFields == loginFields) {
            return
        }
        update.loginFields = loginFields
        _viewState.postValue(update)
    }

    fun setAuthToken( authToken: AuthToken){
        val update = getCurrentViewStateOrNew()
        if(update.authToken == authToken) {
            return
        }
        update.authToken = authToken
        _viewState.postValue(update)
    }

    override fun initNewViewState(): AuthViewState {
        return AuthViewState()
    }

    override fun handleStateEvent(stateEvent: AuthStateEvent): LiveData<DataState<AuthViewState>> {
        return when(stateEvent){

            is LoginAttemptEvent ->{
                AbsentLiveData.create()
            }
            is RegisterAttemptEvent ->{
                AbsentLiveData.create()
            }

            is CheckPreviousAuthEvent ->{
                AbsentLiveData.create()
            }

        }
    }
}

