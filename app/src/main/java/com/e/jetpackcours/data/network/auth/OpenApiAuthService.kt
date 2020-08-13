package com.e.jetpackcours.data.network.auth

import androidx.lifecycle.LiveData
import com.e.jetpackcours.data.network.auth.network_responses.LoginResponse
import com.e.jetpackcours.data.network.auth.network_responses.RegistrationResponse
import com.e.jetpackcours.util.Constants
import com.e.jetpackcours.util.GenericApiResponse
import com.e.jetpackcours.util.LiveDataCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface OpenApiAuthService {


    @POST("account/login")
    @FormUrlEncoded
    fun login(
        @Field("username")  email : String ,
        @Field("password") password : String
    ) : LiveData<GenericApiResponse<LoginResponse>>


    @POST("account/register")
    @FormUrlEncoded
    fun register(
        @Field("email")  email : String ,
        @Field("username") username : String,
        @Field("password")  password : String ,
        @Field("password2") password2 : String
    ) : LiveData<GenericApiResponse<RegistrationResponse>>




    companion object {
        operator fun invoke()  :  OpenApiAuthService{

            val gson : Gson = GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()


                return Retrofit
                    .Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(OpenApiAuthService::class.java)
        }
    }
}