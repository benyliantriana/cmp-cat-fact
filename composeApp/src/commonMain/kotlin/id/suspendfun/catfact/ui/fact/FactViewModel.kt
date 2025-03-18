package id.suspendfun.catfact.ui.fact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.suspendfun.catfact.base.BaseResponse
import id.suspendfun.catfact.repository.FactRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FactViewModel(
    private val factRepository: FactRepository,
) : ViewModel() {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    private var _factUiState = MutableStateFlow<FactUiState>(FactUiState.Loading)
    val factUiState: StateFlow<FactUiState> get() = _factUiState

    // for loading state, because loading state don't have any data
    private var _currentFactResponse = MutableStateFlow(FactUiData("", 0, false))
    val currentFactResponse: StateFlow<FactUiData> get() = _currentFactResponse

    private var _hasMultipleCats = MutableStateFlow(false)
    val hasMultipleCats: StateFlow<Boolean> get() = _hasMultipleCats

    init {
        getSavedFact()
    }

    fun getSavedFact() {
        viewModelScope.launch(ioDispatcher) {
            factRepository.getSavedFact().collect { result ->
                when (result) {
                    is BaseResponse.Loading -> {
                        _factUiState.value = FactUiState.Loading
                    }

                    is BaseResponse.Success -> {
                        _factUiState.value = FactUiState.Success(result.data)
                        _currentFactResponse.value = result.data
                        _hasMultipleCats.value = hasMultipleCats(result.data.fact)
                    }

                    is BaseResponse.Failed -> {
                        _factUiState.value = FactUiState.Failed(result.code, result.message)
                    }
                }
            }
        }
    }

    fun updateFact() {
        viewModelScope.launch(ioDispatcher) {
            factRepository.updateFact().collect { result ->
                when (result) {
                    is BaseResponse.Loading -> {
                        _factUiState.value = FactUiState.Loading
                    }

                    is BaseResponse.Success -> {
                        _factUiState.value = FactUiState.Success(result.data)
                        _currentFactResponse.value = result.data
                        _hasMultipleCats.value = hasMultipleCats(result.data.fact)
                    }

                    is BaseResponse.Failed -> {
                        _factUiState.value = FactUiState.Failed(result.code, result.message)
                    }
                }
            }
        }
    }

    fun saveOrRemoveFactInFavorite(factUiData: FactUiData) {
        viewModelScope.launch(ioDispatcher) {
            factRepository.saveOrRemoveFactInFavoriteDataStore(
                factUiData.copy(isFavorite = !factUiData.isFavorite)
            )
            getSavedFact()
        }
    }

    private fun hasMultipleCats(fact: String): Boolean {
        return fact.contains("cats", true)
    }
}