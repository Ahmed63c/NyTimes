package com.company.nytimes.common.base


import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import artifact.signals_bus.SignalsReceiver
import com.company.nytimes.common.contract.ViewManager
import com.company.nytimes.common.utils.Signal
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
abstract class BaseFragment @JvmOverloads constructor(
    @LayoutRes layoutId: Int = 0
) : Fragment(layoutId) {

    lateinit var signalsReceiver: SignalsReceiver<Signal>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signalsReceiver = SignalsReceiver(viewLifecycleOwner)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()

    }



    protected fun makeToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    protected fun showProgressBar() {
        (requireActivity() as ViewManager).showProgressBar()
    }

    protected fun hideProgressBar() {
        (requireActivity() as ViewManager).hideProgressBar()
    }

}
