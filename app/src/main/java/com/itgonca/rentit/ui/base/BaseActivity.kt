package com.itgonca.rentit.ui.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun isShowLoader(isShow:Boolean)
}