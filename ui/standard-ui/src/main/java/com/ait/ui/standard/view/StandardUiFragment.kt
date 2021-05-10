package com.ait.ui.standard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ait.ui.standard.databinding.FragmentStandardUiBinding

class StandardUiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentStandardUiBinding.inflate(
        inflater,
        container,
        false
    ).also {
        it.lifecycleOwner = viewLifecycleOwner
    }.root
}