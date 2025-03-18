package id.suspendfun.catfact.repository

import id.suspendfun.catfact.base.BaseResponse
import id.suspendfun.catfact.ui.fact.FactUiData
import kotlinx.coroutines.flow.Flow

interface FactRepository {
    suspend fun getSavedFact(): Flow<BaseResponse<FactUiData>>
    suspend fun updateFact(): Flow<BaseResponse<FactUiData>>
    suspend fun saveOrRemoveFactInFavoriteDataStore(fact: FactUiData)
    suspend fun getSavedFavoriteFactList(): Flow<BaseResponse<List<FactUiData>>>
}
