package com.itgonca.rentit.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.itgonca.rentit.R
import com.itgonca.rentit.databinding.ActivityMainBinding
import com.itgonca.rentit.ui.compose.theme.RentItTheme
import com.itgonca.rentit.ui.feature.MainScreen
import com.itgonca.rentit.ui.viewmodel.HomeViewModel
import com.itgonca.rentit.utils.view.LoaderDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    //private val viewModel: MainViewModel by viewModels()
    private val viewModel: HomeViewModel by viewModels()
    private var navController: NavController? = null
    private var dialogLoader: LoaderDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            //val email by viewModel.email.collectAsState()
            //val password by viewModel.password.collectAsState()
            //val step by viewModel.step.collectAsState()
            //val listLocations by viewModel.listLocations.collectAsState()
            RentItTheme {
                /*LoginScreen(
                    email = email,
                    password = password,
                    step = step,
                    onEmailChange = { viewModel.onEmailChange(it) },
                    onPasswordChange = { viewModel.onPasswordChange(it) },
                    onStepChange = { viewModel.onStepChange(it) }
                )*/
                /*HomeScreen(viewModel.getFilters(),listLocations) {
                    Log.i("TAG", "onSearh: $it ")
                }*/
               MainScreen(listFilters = viewModel.getFilters(), onQueryChange ={} )
            }
        }
        /*binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
        viewModel.isSessionActive()*/
    }

    private fun initComponents() {
        dialogLoader = LoaderDialog.newInstance()
        navController = Navigation.findNavController(this, R.id.navHostContainer)
        NavigationUI.setupWithNavController(binding.bottomNav, navController!!)
        //hideStatusBar()
    }

    /*override fun isShowLoader(isShow: Boolean) {
        if (isShow)
           dialogLoader?.show(supportFragmentManager,"Loader")
        else
            dialogLoader?.dismiss()
    }*/
}