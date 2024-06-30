package viewmodel;

import com.app.compose_navigation_mvvm_flow.utils.UiState
import network.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import models.ApiResponse
import models.Products
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class HomeViewModel : BaseViewModel(),KoinComponent {

    val repository: Repository by inject()

    val _uiStateProductList = MutableStateFlow<UiState<ApiResponse?>>(UiState.Loading)
    val uiStateProductList: StateFlow<UiState<ApiResponse?>> = _uiStateProductList

    fun getProductList() = CoroutineScope(Dispatchers.IO).launch {
        fetchData(_uiStateProductList) { repository.getProducts() }
    }

}