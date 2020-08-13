package com.e.jetpackcours.ui.auth

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.jetpackcours.activityScopedFragmentViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

abstract class BaseAuthFragment(layout : Int)   : Fragment(layout) , KodeinAware{


    override val kodein by closestKodein()

    val TAG : String =" AppDebug"


    val viewModel : AuthViewModel by activityScopedFragmentViewModel()

    //viewModels(ownerProducer = { requireParentFragment() })


    private val viewModeFactory: ViewModelProvider.Factory by instance()


}