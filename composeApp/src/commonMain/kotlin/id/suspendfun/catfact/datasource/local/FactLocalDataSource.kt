package id.suspendfun.catfact.datasource.local

import id.suspendfun.catfact.base.BaseResponse
import id.suspendfun.catfact.ui.fact.FactUiData

interface FactLocalDataSource {
    suspend fun getLocalFact(): BaseResponse<FactUiData>
    suspend fun saveFactToDataStore(factData: FactUiData)
    suspend fun saveOrRemoveFactInFavoriteDataStore(factData: FactUiData)
    suspend fun isFavoriteFact(factData: FactUiData): Boolean
    suspend fun getSavedFavoriteFactList(): BaseResponse<List<FactUiData>>
}
