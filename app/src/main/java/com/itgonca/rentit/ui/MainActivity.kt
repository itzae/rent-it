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
import com.itgonca.rentit.utils.view.LoaderDialog
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: LoginViewModel by viewModels()
    private var navController: NavController? = null
    private var dialogLoader: LoaderDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
        viewModel.isSessionActive()
    }

    private fun initComponents(){
        dialogLoader = LoaderDialog.newInstance()
        navController = Navigation.findNavController(this, R.id.navHostContainer)
        hideStatusBar()
    }

    override fun isShowLoader(isShow: Boolean) {
        if (isShow)
           dialogLoader?.show(supportFragmentManager,"Loader")
        else
            dialogLoader?.dismiss()
    }
}