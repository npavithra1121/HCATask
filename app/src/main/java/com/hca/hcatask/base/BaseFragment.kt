package com.hca.hcatask.base

import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    fun showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireActivity(), message, length).show()
    }

}