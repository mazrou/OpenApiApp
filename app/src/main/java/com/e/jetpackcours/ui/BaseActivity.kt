package com.e.jetpackcours.ui

import androidx.appcompat.app.AppCompatActivity
import com.e.jetpackcours.data.session.SessionManager
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

abstract class BaseActivity : AppCompatActivity()  , KodeinAware{

    override val kodein: Kodein by closestKodein()

    protected  val sessionManager : SessionManager by instance()

    val TAG : String = "AppDebug"
}