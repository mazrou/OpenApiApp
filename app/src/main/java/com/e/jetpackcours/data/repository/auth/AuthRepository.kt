package com.e.jetpackcours.data.repository.auth

import androidx.lifecycle.LiveData
import com.e.jetpackcours.data.network.auth.OpenApiAuthService
import com.e.jetpackcours.data.network.auth.network_responses.LoginResponse
import com.e.jetpackcours.data.network.auth.network_responses.RegistrationResponse
import com.e.jetpackcours.data.persistence.AccountPropertiesDao
import com.e.jetpackcours.data.persistence.AuthTokenDao
import com.e.jetpackcours.data.session.SessionManager
import com.e.jetpackcours.util.GenericApiResponse
import retrofit2.http.Field

class AuthRepository
constructor(
   private val authTokenDao: AuthTokenDao,
   private val accountPropertiesDao: AccountPropertiesDao,
   private val openApiAuthService: OpenApiAuthService,
   private val sessionManager: SessionManager
){


   fun testLoginRequest(email : String , password : String): LiveData<GenericApiResponse<LoginResponse>> {
      return  openApiAuthService.login(email , password)
   }


   fun testResigter (
        email : String,
      username : String,
        password : String,
      password2 : String
   ) : LiveData<GenericApiResponse<RegistrationResponse>>{
      return openApiAuthService.register(email , username , password , password2)
   }
}