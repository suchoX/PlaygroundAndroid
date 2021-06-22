package com.sucho.playground.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStoreOwner
import com.sucho.playground.R
import com.sucho.playground.databinding.FragmentHomeBinding
import com.sucho.playground.ui.activity.main.MainActivity
import com.sucho.playground.ui.activity.main.MainViewModel
import com.sucho.playground.ui.base.BaseFragment
import com.sucho.playground.ui.fragment.home.HomeViewState.SetKanyeQuote
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel, MainViewModel>() {

  private var fragmentView: View? = null

  override fun getViewModelClass(): Class<HomeFragmentViewModel> = HomeFragmentViewModel::class.java

  override fun getActivityViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

  override fun getActivityViewModelOwner(): ViewModelStoreOwner =
    mainActivity ?: (activity as MainActivity)

  override fun layoutId(): Int = R.layout.fragment_home

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    if (fragmentView == null) {
      fragmentView = super.onCreateView(inflater, container, savedInstanceState)
      init()
    }
    return fragmentView
  }

  private fun init() {
    initListeners()
    initObservers()
  }

  private fun initListeners() {
    viewModel.fetchKanyeQuotesPeriodically()
    viewModel.fetchSwansonQuotesPeriodically()
  }

  private fun initObservers() {
    viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
      when(state) {
        is SetKanyeQuote -> {
          binding.kanyeQuoteView.setQuoteText(state.kanyeQuoteWithImage.quote)
          binding.kanyeQuoteView.setKanyeImage(state.kanyeQuoteWithImage.imageResId)
        }
      }
    })
  }

}