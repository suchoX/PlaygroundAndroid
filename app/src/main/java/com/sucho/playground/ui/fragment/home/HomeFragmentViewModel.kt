package com.sucho.playground.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sucho.data.usecase.GetKanyeQuoteWithImageUseCase
import com.sucho.data.usecase.GetSwansonQuoteUseCase
import com.sucho.domain.model.KanyeQuoteWithImage
import com.sucho.domain.model.SwansonQuoteWithImage
import com.sucho.playground.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val kanyeQuoteWithImageUseCase: GetKanyeQuoteWithImageUseCase,
  private val swansonQuoteUseCase: GetSwansonQuoteUseCase
) : BaseViewModel() {
  private var _viewState: MutableLiveData<HomeViewState> = MutableLiveData()
  val viewState: LiveData<HomeViewState> = _viewState

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
}

sealed class HomeViewState {
  class SetKanyeQuote(val kanyeQuoteWithImage: KanyeQuoteWithImage) : HomeViewState()
  class SetSwansonQuote(val swansonQuoteWithImage: SwansonQuoteWithImage): HomeViewState()
}