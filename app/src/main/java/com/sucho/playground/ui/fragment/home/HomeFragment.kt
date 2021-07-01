package com.sucho.playground.ui.fragment.home

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.constraintlayout.motion.widget.MotionLayout
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel, MainViewModel>() {

  private var fragmentView: View? = null
  private lateinit var quotesAdapter: QuotesAdapter
  private lateinit var savedQuotesAdapter: SavedQuotesAdapter

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
        viewModel.saveQuote(quote, type, imgRes)
        binding.animationView.apply {
          visibility = View.VISIBLE
          playAnimation()
          addAnimatorListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
              binding.quotesRecyclerView.alpha = 0.8f
            }

            override fun onAnimationEnd(animation: Animator?) {
              binding.quotesRecyclerView.alpha = 1f
              visibility = View.INVISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationRepeat(animation: Animator?) {}

          })
        }
      }
    }
    binding.quotesRecyclerView.apply {
      adapter = quotesAdapter
    }

    savedQuotesAdapter = SavedQuotesAdapter()
    binding.savedQuotesRecyclerView.apply {
      adapter = savedQuotesAdapter
    }
  }

  private fun initListeners() {
    viewModel.fetchKanyeQuotesPeriodically()
    viewModel.fetchSwansonQuotesPeriodically()
    viewModel.fetchWalterWhiteQuotesPeriodically()
    viewModel.getQuotes()

    binding.fab.cardBackgroundColor
    binding.fab.setOnClickListener {
      binding.motionLayout.transitionToEnd()
    }

    binding.headingImage.setOnClickListener {
      savedQuotesAdapter.clearSavedQuotes()
      binding.motionLayout.transitionToStart()
    }

    binding.motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
      override fun onTransitionStarted(p0: MotionLayout?, startId: Int, p2: Int) {}

      override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {}

      override fun onTransitionCompleted(p0: MotionLayout?, currentId: Int) {
        if (currentId == R.id.end) {
          Handler().postDelayed({
            savedQuotesAdapter.setSavedQuotes(viewModel.savedQuotes)
            animateSavedQuotesRecyclerviewItems(R.anim.layout_animation_saved_quote)
          }, 100)
        }
      }

      override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}
    })
  }

  private fun initObservers() {
    viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
      when (state) {
        is SetKanyeQuote -> {
          quotesAdapter.kanyeQuoteWithImage = state.kanyeQuoteWithImage
          quotesAdapter.notifyItemChanged(0)
        }
        is SetSwansonQuote -> {
          quotesAdapter.swansonQuoteWithImage = state.swansonQuoteWithImage
          quotesAdapter.notifyItemChanged(1)
        }
        is SetWalterWhiteQuote -> {
          quotesAdapter.walterWhiteQuoteWithImage = state.walterWhiteQuoteWithImage
          quotesAdapter.notifyItemChanged(2)
        }
      }
    })
  }

  private fun animateSavedQuotesRecyclerviewItems(@AnimRes animId: Int){
    binding.savedQuotesRecyclerView.apply {
      layoutAnimation = AnimationUtils.loadLayoutAnimation(context, animId)
      adapter?.notifyDataSetChanged()
      scheduleLayoutAnimation()
    }
  }

}