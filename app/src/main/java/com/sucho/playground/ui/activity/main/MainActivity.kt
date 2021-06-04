package com.sucho.playground.ui.activity.main

import android.os.Bundle
import com.sucho.playground.R
import com.sucho.playground.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {

  override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}