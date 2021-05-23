package com.ait.ui.standard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ait.ui.standard.databinding.FragmentStandardUiBinding
import com.ait.ui.standard.viewmodel.StandardUiViewModel

class StandardUiFragment : Fragment() {

    private val viewModel: StandardUiViewModel by viewModels()

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
        it.viewModel = viewModel
    }.root
}