package com.orbitalsonic.generalproject.ui.fragments.base

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController


abstract class BaseNavFragment : FragmentGeneral() {

    /**
     *  @since : Write Code for BackPress Functionality
     */
    override fun onResume() {
        super.onResume()
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
//                debugToast("handleOnBackPressed()")
                onBackPressed()
                this.remove()
            }
        }
        (context as FragmentActivity).onBackPressedDispatcher.addCallback(this, callback)
    }

  /*  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                showToast("onBackPressed()")
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }*/
    abstract fun onBackPressed()

    /**
     *     Used launchWhenCreated, bcz of screen rotation
     *     Used launchWhenResumed, bcz of screen rotation
     * @param fragment_id : Current Fragment's Id (from Nav Graph)
     * @param action : Action / Id of other fragment
     */

    protected fun navigateTo(fragment_id: Int, action: Int) {
        lifecycleScope.launchWhenCreated {
            if (isAdded && isCurrentDestination(fragment_id)) {
                findNavController().navigate(action)
            }
        }
    }

    protected fun navigateTo(fragment_id: Int, action: NavDirections) {
        lifecycleScope.launchWhenCreated {
            if (isAdded && isCurrentDestination(fragment_id)) {
                findNavController().navigate(action)
            }
        }
    }

    protected fun popFrom(fragment_id: Int) {
        lifecycleScope.launchWhenCreated {
            if (isAdded && isCurrentDestination(fragment_id)) {
                findNavController().popBackStack()
            }
        }
    }

    private fun isCurrentDestination(fragment_id: Int): Boolean {
        return findNavController().currentDestination?.id == fragment_id
    }
}