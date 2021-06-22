package com.sucho.playground.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sucho.data.usecase.GetKanyeQuoteWithImageUseCase
import com.sucho.domain.model.KanyeQuoteWithImage
import com.sucho.playground.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val kanyeQuoteWithImageUseCase: GetKanyeQuoteWithImageUseCase
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
}

sealed class HomeViewState {
  class SetKanyeQuote(val kanyeQuoteWithImage: KanyeQuoteWithImage) : HomeViewState()
}