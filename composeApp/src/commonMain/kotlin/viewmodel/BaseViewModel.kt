package viewmodel

import androidx.lifecycle.ViewModel
import com.app.compose_navigation_mvvm_flow.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


open class BaseViewModel: ViewModel() {

  suspend fun <T> fetchData(uiStateFlow: MutableStateFlow<UiState<T?>>, apiCall: suspend () -> Flow<UiState<T?>>) {
      uiStateFlow.value = UiState.Loading
    try {
       apiCall().collect {
         uiStateFlow.value = it
      }
    } catch (e: Exception) {
       uiStateFlow.value = UiState.Error(e.message?:"")
    }
  }
}