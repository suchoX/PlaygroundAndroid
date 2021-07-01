package com.sucho.playground.ui.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sucho.data.db.entity.QuoteEntity
import com.sucho.data.usecase.GetKanyeQuoteWithImageUseCase
import com.sucho.data.usecase.GetQuotesUseCase
import com.sucho.data.usecase.GetSwansonQuoteUseCase
import com.sucho.data.usecase.GetWalterWhiteQuoteWithImageUseCase
import com.sucho.data.usecase.SaveQuoteUseCase
import com.sucho.domain.model.KanyeQuoteWithImage
import com.sucho.domain.model.SwansonQuoteWithImage
import com.sucho.domain.model.WalterWhiteQuoteWithImage
import com.sucho.playground.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val kanyeQuoteWithImageUseCase: GetKanyeQuoteWithImageUseCase,
  private val swansonQuoteUseCase: GetSwansonQuoteUseCase,
  private val walterWhiteQuoteWithImageUseCase: GetWalterWhiteQuoteWithImageUseCase,
  private val saveQuoteUseCase: SaveQuoteUseCase,
  private val getQuotesUseCase: GetQuotesUseCase
) : BaseViewModel() {
  private var _viewState: MutableLiveData<HomeViewState> = MutableLiveData()
  val viewState: LiveData<HomeViewState> = _viewState
  val savedQuotes: ArrayList<QuoteEntity> = ArrayList()

  fun fetchKanyeQuotesPeriodically() {
    viewModelScope.launch {
      kanyeQuoteWithImageUseCase.perform().collect { kanyeQuoteWithImage ->
        _viewState.value = HomeViewState.SetKanyeQuote(kanyeQuoteWithImage)
      }
    }
  }

  fun fetchSwansonQuotesPeriodically() {
    viewModelScope.launch {
      swansonQuoteUseCase.perform().collect { swansonQuoteWithImage ->
        _viewState.value = HomeViewState.SetSwansonQuote(swansonQuoteWithImage)
      }
    }
  }

  fun fetchWalterWhiteQuotesPeriodically() {
    viewModelScope.launch {
        walterWhiteQuoteWithImageUseCase.perform().collect { walterWhiteQuoteWithImage ->
          _viewState.value = HomeViewState.SetWalterWhiteQuote(walterWhiteQuoteWithImage)
      }
    }
  }

  fun saveQuote(quote: String, type: Int, imgRes: Int) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        saveQuoteUseCase.perform(QuoteEntity(0, type, quote, imgRes))
      }
    }
  }

  fun getQuotes() {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        savedQuotes.clear()
        savedQuotes.addAll(getQuotesUseCase.perform())
      }
    }
  }
}

sealed class HomeViewState {
  class SetKanyeQuote(val kanyeQuoteWithImage: KanyeQuoteWithImage) : HomeViewState()
  class SetSwansonQuote(val swansonQuoteWithImage: SwansonQuoteWithImage) : HomeViewState()
  class SetWalterWhiteQuote(val walterWhiteQuoteWithImage: WalterWhiteQuoteWithImage) : HomeViewState()
}