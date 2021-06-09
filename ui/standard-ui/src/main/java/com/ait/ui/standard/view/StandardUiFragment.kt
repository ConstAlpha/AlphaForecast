package com.ait.ui.standard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ait.ui.standard.R
import com.ait.ui.standard.adapter.ForecastDataAdapter
import com.ait.ui.standard.databinding.FragmentStandardUiBinding
import com.ait.ui.standard.viewmodel.StandardUiViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class StandardUiFragment : Fragment() {

    private val viewModel: StandardUiViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentStandardUiBinding.inflate(
        inflater,
        container,
        false
    ).also {
        viewModel.weatherInfo.observe(viewLifecycleOwner, { info ->
            val recyclerView = (view as View).findViewById<RecyclerView>(R.id.mainRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter =
                ForecastDataAdapter(info, { time -> viewModel.changeDayTime(time) })
        })
        it.lifecycleOwner = viewLifecycleOwner
        it.viewModel = viewModel
    }.root
}