package com.e.jetpackcours.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.jetpackcours.activityScopedFragmentViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

abstract class BaseAuthFragment(layout : Int)   : Fragment(layout) , KodeinAware{


    override val kodein by closestKodein()

    val TAG : String =" AppDebug"


    lateinit var viewModel : AuthViewModel

    //viewModels(ownerProducer = { requireParentFragment() })


    //private val viewModeFactory: ViewModelProvider.Factory by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as AuthActivity ).viewModel
    }



}