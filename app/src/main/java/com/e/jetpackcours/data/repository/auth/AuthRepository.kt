package com.e.jetpackcours.data.repository.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import com.e.jetpackcours.data.model.AuthToken
import com.e.jetpackcours.data.network.auth.OpenApiAuthService
import com.e.jetpackcours.data.network.auth.network_responses.LoginResponse
import com.e.jetpackcours.data.network.auth.network_responses.RegistrationResponse
import com.e.jetpackcours.data.persistence.AccountPropertiesDao
import com.e.jetpackcours.data.persistence.AuthTokenDao
import com.e.jetpackcours.data.session.SessionManager
import com.e.jetpackcours.ui.DataState
import com.e.jetpackcours.ui.Response
import com.e.jetpackcours.ui.ResponseType
import com.e.jetpackcours.ui.auth.state.AuthViewState
import com.e.jetpackcours.util.ErrorHandling.Companion.ERROR_UNKNOWN
import com.e.jetpackcours.util.GenericApiResponse
import retrofit2.http.Field

class AuthRepository
constructor(
   private val authTokenDao: AuthTokenDao,
   private val accountPropertiesDao: AccountPropertiesDao,
   private val openApiAuthService: OpenApiAuthService,
   private val sessionManager: SessionManager
){

   fun attemptRegistration(
      email: String ,
      username: String,
      password: String ,
      confirmPassword :String
   ) : LiveData<DataState<AuthViewState>>{
      return openApiAuthService.register(
         email = email , username = username  , password = password , password2 = confirmPassword
      ).switchMap { response -> object : LiveData<DataState<AuthViewState>>(){
         override fun onActive() {
            super.onActive()
            when (response){
               is GenericApiResponse.ApiSuccessResponse ->{
                  value = DataState.data(
                     data = AuthViewState(
                        authToken = AuthToken(
                           response.body.pk ,
                           response.body.token
                        )
                     ) ,
                     response = null
                  )
               }
               is GenericApiResponse.ApiErrorResponse ->{
                  value = DataState.error(
                     response = Response(
                        message = response.errorMessage ,
                        responseType = ResponseType.Dialog()
                     )
                  )
               }
               is GenericApiResponse.ApiEmptyResponse ->{
                  value = DataState.error(
                     response = Response(
                        message = ERROR_UNKNOWN ,
                        responseType = ResponseType.Dialog()
                     )
                  )
               }
            }
         }
      }
      }
   }

   fun attemptLogin(email: String , password: String) : LiveData<DataState<AuthViewState>>{
      return openApiAuthService.login(
         email = email , password = password
      ).switchMap { response -> object : LiveData<DataState<AuthViewState>>(){
         override fun onActive() {
            super.onActive()
            when (response){
               is GenericApiResponse.ApiSuccessResponse ->{
                  value = DataState.data(
                     data = AuthViewState(
                        authToken = AuthToken(
                           response.body.pk ,
                           response.body.token
                     )
                  ) ,
                     response = null
                  )
               }
               is GenericApiResponse.ApiErrorResponse ->{
                  value = DataState.error(
                     response = Response(
                        message = response.errorMessage ,
                        responseType = ResponseType.Dialog()
                     )
                  )
               }
               is GenericApiResponse.ApiEmptyResponse ->{
                  value = DataState.error(
                     response = Response(
                        message = ERROR_UNKNOWN ,
                        responseType = ResponseType.Dialog()
                     )
                  )
               }
            }
         }
      }
      }
   }
}