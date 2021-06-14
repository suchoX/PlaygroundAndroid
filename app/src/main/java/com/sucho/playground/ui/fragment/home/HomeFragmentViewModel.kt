package com.sucho.playground.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.sucho.data.usecase.GetKanyeQuoteUseCase
import com.sucho.playground.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val kanyeQuoteUseCase: GetKanyeQuoteUseCase
) : BaseViewModel() {
  private var _viewState: MutableLiveData<HomeViewState> = MutableLiveData()
  val viewState: LiveData<HomeViewState> = _viewState
}

sealed class HomeViewState {
  object Loading : HomeViewState()
  class Error(val message: String) : HomeViewState()
}