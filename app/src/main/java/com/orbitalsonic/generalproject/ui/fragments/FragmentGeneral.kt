package com.orbitalsonic.generalproject.ui.fragments

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.orbitalsonic.generalproject.BuildConfig
import com.orbitalsonic.generalproject.helpers.firebase.FirebaseUtils.recordException

open class FragmentGeneral : Fragment() {

    private val generalTAG = "GeneralTAG"

    fun withDelay(delay: Long = 300, block: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(block, delay)
    }

    fun getResString(stringId: Int): String {
        return context?.resources?.getString(stringId) ?: ""
    }

    /* -------------------------------- Toast -------------------------------- */

    fun showToast(message: String) {
        activity?.let {
            try {
                it.runOnUiThread {
                    Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
                }
            } catch (ex: Exception) {
                ex.recordException("showToast : ${it.javaClass.simpleName}")
            }
        }
    }

    fun debugToast(message: String) {
        activity?.let {
            try {
                it.runOnUiThread {
                    if (BuildConfig.DEBUG) {
                        Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (ex: Exception) {
                ex.recordException("debugToast : ${it.javaClass.simpleName}")
            }
        }
    }

    fun showToast(stringId: Int) {
        val message = getResString(stringId)
        showToast(message)
    }

    /* -------------------------------- Snackbar -------------------------------- */

    fun showSnackBar(message: String) {
        this.view?.let { v ->
            activity?.let {
                try {
                    it.runOnUiThread {
                        Snackbar.make(v.rootView, message, Snackbar.LENGTH_SHORT).show()
                    }
                } catch (ex: Exception) {
                    ex.recordException("showSnackBar : ${it.javaClass.simpleName}")
                }
            }
        }
    }

    /* -------------------------------- Logs -------------------------------- */

    fun d(tag: String = generalTAG, message: String) {
        Log.d(tag, message)
    }

    fun e(tag: String = generalTAG, message: String) {
        Log.e(tag, message)
    }

    fun i(tag: String = generalTAG, message: String) {
        Log.i(tag, message)
    }

    fun w(tag: String = generalTAG, message: String) {
        Log.w(tag, message)
    }

    fun v(tag: String = generalTAG, message: String) {
        Log.v(tag, message)
    }



}