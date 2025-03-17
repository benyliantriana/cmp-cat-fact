package id.suspendfun.catfact.ui.fact

sealed class FactUiState {
    data object Loading : FactUiState()
    data class Success(val factUiData: FactUiData) : FactUiState()
    data class Failed(val code: Int, val message: String) : FactUiState()
}
