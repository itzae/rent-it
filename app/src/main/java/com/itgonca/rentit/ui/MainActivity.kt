package com.itgonca.rentit.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.itgonca.rentit.R
import com.itgonca.rentit.databinding.ActivityMainBinding
import com.itgonca.rentit.ui.base.BaseActivity
import com.itgonca.rentit.ui.viewmodel.LoginViewModel
import com.itgonca.rentit.utils.extension.hideStatusBar
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: LoginViewModel by viewModels()
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.navHostContainer)
        viewModel.isSessionActive()
        hideStatusBar()
    }

    override fun isShowLoader(isShow: Boolean) {
        if (isShow)
            navController?.navigate(R.id.loaderDialog)
        else
            navController?.popBackStack(R.id.loaderDialog, true)
    }
}