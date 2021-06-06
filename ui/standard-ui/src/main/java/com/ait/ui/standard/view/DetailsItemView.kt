package com.ait.ui.standard.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ait.ui.standard.R
import com.ait.ui.standard.databinding.DetailsItemLayoutBinding

class DetailsItemViewModel : ViewModel() {
    val title = MutableLiveData("3213123")
}

class DetailsItemView constructor(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {
//    private var binding: DetailsItemLayoutBinding
    private val imageView: ImageView
    private val titleTextView: TextView
    private val valueTextView: TextView

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.details_item_layout, this, true)
//        binding = DetailsItemLayoutBinding.inflate(inflater)
//        binding.viewModel = DetailsItemViewModel()
        imageView = findViewById(R.id.item_icon)
        titleTextView = findViewById(R.id.item_title)
        valueTextView = findViewById(R.id.item_value)
    }

    fun setIcon(drawable: Drawable?) {
        imageView.setImageDrawable(drawable)
    }

    fun setTitle(title: String) {
        titleTextView.text = title
    }

    fun setValue(value: String) {
        valueTextView.text = value
    }
}