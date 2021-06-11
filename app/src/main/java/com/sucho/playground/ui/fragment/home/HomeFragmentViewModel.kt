package com.sucho.playground.ui.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sucho.data.remote.SafeResult
import com.sucho.data.usecase.GetKanyeQuoteUseCase
import com.sucho.data.usecase.GetTestDataUseCase
import com.sucho.domain.model.TestModel
import com.sucho.playground.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val testDataUseCase: GetTestDataUseCase,
  private val kanyeQuoteUseCase: GetKanyeQuoteUseCase
): BaseViewModel() {
  private var _viewState: MutableLiveData<HomeViewState> = MutableLiveData()
  val viewState: LiveData<HomeViewState> = _viewState

  fun loadTestData() {
    _viewState.value = HomeViewState.Loading
    viewModelScope.launch {
      val data = testDataUseCase.perform()
      _viewState.value = HomeViewState.ShowData(data)
    }
  }
}

sealed class HomeViewState {
  object Loading : HomeViewState()
  class ShowData(val testData: TestModel) : HomeViewState()
  class Error(val message: String) : HomeViewState()
}