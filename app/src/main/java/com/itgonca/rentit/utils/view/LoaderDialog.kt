package com.itgonca.rentit.utils.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.itgonca.rentit.R
import com.itgonca.rentit.databinding.DialogLoaderBinding

class LoaderDialog : DialogFragment() {
    private var _binding: DialogLoaderBinding? = null
    private val binding get() = _binding!!

    companion object {
        val TAG = LoaderDialog::class.java.simpleName
        fun newInstance(): LoaderDialog = LoaderDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogLoaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}