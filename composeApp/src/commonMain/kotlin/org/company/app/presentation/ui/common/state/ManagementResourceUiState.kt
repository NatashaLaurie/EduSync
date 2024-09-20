package org.company.app.presentation.ui.common.state

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.company.app.presentation.model.ResourceUiState

@Composable
fun <T> ManagementResourceUiState(
    modifier: Modifier = Modifier,
    resourceUiState: ResourceUiState<T>,
    successView: @Composable (data: T) -> Unit,
    loadingView: @Composable () -> Unit = { Loading() },
    onTryAgain: () -> Unit,
    msgTryAgain: String = "Нет данных",
    onCheckAgain: () -> Unit,
    msgCheckAgain: String = "Курсы не добавлены"
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        when (resourceUiState) {
            is ResourceUiState.Empty -> Empty(modifier = modifier, onCheckAgain = onCheckAgain, msg = msgCheckAgain)
            is ResourceUiState.Error -> Error(modifier = modifier, onTryAgain = onTryAgain, msg = msgTryAgain)
            ResourceUiState.Loading -> loadingView()
            is ResourceUiState.Success -> successView(resourceUiState.data)
            ResourceUiState.Idle -> Unit
        }
    }
}