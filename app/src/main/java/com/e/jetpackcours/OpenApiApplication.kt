package com.e.jetpackcours

import android.app.Application

import com.e.jetpackcours.data.network.auth.OpenApiAuthService
import com.e.jetpackcours.data.persistence.AccountPropertiesDao
import com.e.jetpackcours.data.persistence.AppDatabase
import com.e.jetpackcours.data.persistence.AuthTokenDao
import com.e.jetpackcours.data.repository.auth.AuthRepository
import com.e.jetpackcours.data.session.SessionManager
import com.e.jetpackcours.ui.auth.AuthViewModel

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class OpenApiApplication : Application() , KodeinAware {
    override val kodein: Kodein = Kodein.lazy {

        import(androidXModule(this@OpenApiApplication))

        //Api
        bind<OpenApiAuthService>() with singleton { OpenApiAuthService() }

        //persistence
        bind() from singleton { AppDatabase(instance()) }
        bind<AccountPropertiesDao>() with singleton { instance<AppDatabase>().getAccountPropertiesDao() }
        bind<AuthTokenDao>() with singleton { instance<AppDatabase>().getAuthTokenDao() }

        //Session Manager
        bind() from singleton { SessionManager(instance(), instance()) }

        //Repository
        bind() from singleton { AuthRepository(instance(), instance(), instance(), instance()) }

        //ViewModels
        //bindViewModel<AuthViewModel>() with provider { AuthViewModel(instance()) }
        // bind<AuthViewModel>(AuthViewModel::class.java.simpleName)#
        import(viewModelModule)
    }

    //adapter


    private val viewModelModule = Kodein.Module("viewModel") {
        bindViewModel<AuthViewModel>() with provider { AuthViewModel(instance()) }

    }

}





