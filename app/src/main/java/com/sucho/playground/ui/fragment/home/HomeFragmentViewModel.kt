package com.sucho.playground.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sucho.data.remote.SafeResult.Success
import com.sucho.data.usecase.GetKanyeQuoteUseCase
import com.sucho.domain.model.KanyeQuote
import com.sucho.playground.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val kanyeQuoteUseCase: GetKanyeQuoteUseCase
) : BaseViewModel() {
  private var _viewState: MutableLiveData<HomeViewState> = MutableLiveData()
  val viewState: LiveData<HomeViewState> = _viewState

  fun fetchKanyeQuotesPeriodically() {
    viewModelScope.launch {
      kanyeQuoteUseCase.perform().collect { result ->
        when (result) {
          is Success -> {
            _viewState.value = HomeViewState.SetKanyeQuote(result.data)
          }
        }
      }
    }
  }
}

sealed class HomeViewState {
  object Loading : HomeViewState()
  class Error(val message: String) : HomeViewState()
  class SetKanyeQuote(val kanyeQuote: KanyeQuote) : HomeViewState()
}