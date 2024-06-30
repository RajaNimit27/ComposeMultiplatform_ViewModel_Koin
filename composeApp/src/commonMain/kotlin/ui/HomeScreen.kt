package ui
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.compose_navigation_mvvm_flow.utils.UiState
import models.ApiResponse
import models.Products
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel
import viewmodel.HomeViewModel

@Composable
fun HomeScreen(){
    val viewModel: HomeViewModel= koinViewModel()
    val state = viewModel.uiStateProductList.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getProductList()
    }
    when (state.value) {
        is UiState.Error -> {
            ProgressLoader(isLoading = false)
        }
        UiState.Loading -> {
            ProgressLoader(isLoading = true)
        }
        is UiState.Success -> {
            ProgressLoader(isLoading = false)
            (state.value as UiState.Success<ApiResponse>).data?.let {
                ProductCard(it.list)
            }
        }
    }
}
