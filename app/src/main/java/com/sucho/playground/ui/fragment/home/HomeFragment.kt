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
import com.sucho.playground.ui.fragment.home.HomeViewState.SetSwansonQuote
import com.sucho.playground.ui.fragment.home.HomeViewState.SetWalterWhiteQuote
import com.sucho.playground.utils.smoothScrollToPositionWithSpeed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel, MainViewModel>() {

  private var fragmentView: View? = null
  private lateinit var quotesAdapter: QuotesAdapter

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
    initUI()
    initListeners()
    initObservers()
  }

  private fun initUI() {
    quotesAdapter = QuotesAdapter()
    quotesAdapter.quoteClickListener = object : QuoteClickListener {
      override fun saveQuote(quote: String, type: Int, imgRes: Int) {
        viewModel.saveQuote(quote,type, imgRes)
      }

    }
    binding.quotesRecyclerView.apply {
      adapter = quotesAdapter
    }
  }

  private fun initListeners() {
    viewModel.fetchKanyeQuotesPeriodically()
    viewModel.fetchSwansonQuotesPeriodically()
    viewModel.fetchWalterWhiteQuotesPeriodically()
    viewModel.getQuotes()
  }

  private fun initObservers() {
    viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
      when (state) {
        is SetKanyeQuote -> quotesAdapter.kanyeQuoteWithImage = state.kanyeQuoteWithImage
        is SetSwansonQuote -> quotesAdapter.swansonQuoteWithImage = state.swansonQuoteWithImage
        is SetWalterWhiteQuote -> quotesAdapter.walterWhiteQuoteWithImage = state.walterWhiteQuoteWithImage
      }
      quotesAdapter.notifyDataSetChanged()
    })
  }

}