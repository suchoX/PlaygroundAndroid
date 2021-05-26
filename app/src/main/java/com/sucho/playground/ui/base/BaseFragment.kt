package com.sucho.playground.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.sucho.playground.BR
import com.sucho.playground.ui.activity.main.MainActivity

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel, AVM : ViewModel>: Fragment() {
  protected lateinit var binding: B
  lateinit var viewModel: VM
  lateinit var activityViewModel: AVM
  protected var mainActivity: MainActivity? = null
  var viewModelFactory= ViewModelProvider.NewInstanceFactory()

  override fun onAttach(context: Context) {
    if (activity is MainActivity) mainActivity = activity as MainActivity
    super.onAttach(context)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
    bindContentView()
    return binding.root
  }

  private fun bindContentView() {
    viewModel = ViewModelProvider(this, viewModelFactory)
        .get(getViewModelClass())
    activityViewModel = ViewModelProvider(getActivityViewModelOwner(), viewModelFactory)
        .get(getActivityViewModelClass())
    binding.setVariable(BR.viewModel, viewModel)
  }

  abstract fun getViewModelClass(): Class<VM>

  abstract fun getActivityViewModelClass(): Class<AVM>

  abstract fun getActivityViewModelOwner(): ViewModelStoreOwner

  @LayoutRes
  protected abstract fun layoutId(): Int
}