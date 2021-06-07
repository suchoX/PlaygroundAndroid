package com.sucho.playground.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<VM : ViewModel> : AppCompatActivity() {
  lateinit var viewModel: VM
  private var viewModelFactory= ViewModelProvider.NewInstanceFactory()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = ViewModelProvider(this)
        .get(getViewModelClass())
  }
  abstract fun getViewModelClass(): Class<VM>
}