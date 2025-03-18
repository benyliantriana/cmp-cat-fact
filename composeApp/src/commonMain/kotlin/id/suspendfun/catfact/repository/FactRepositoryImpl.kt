package id.suspendfun.catfact.repository

import id.suspendfun.catfact.base.BaseResponse
import id.suspendfun.catfact.base.exception.remoteExceptionHandler
import id.suspendfun.catfact.datasource.local.FactLocalDataSource
import id.suspendfun.catfact.datasource.remote.FactRemoteDataSource
import id.suspendfun.catfact.ui.fact.FactUiData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FactRepositoryImpl(
    private val factLocalDataSource: FactLocalDataSource,
    private val factRemoteDataSource: FactRemoteDataSource,
) : FactRepository {
    override suspend fun getSavedFact(): Flow<BaseResponse<FactUiData>> = flow {
        emit(BaseResponse.Loading)
        emit(getStoredFact())
    }

    override suspend fun updateFact(): Flow<BaseResponse<FactUiData>> = flow {
        emit(BaseResponse.Loading)
        val remoteFact = getRemoteFact()
        if (remoteFact is BaseResponse.Success) {
            val isFavoriteFact = factLocalDataSource.isFavoriteFact(remoteFact.data)
            val factData = remoteFact.data.copy(isFavorite = isFavoriteFact)
            storeFact(factData)
            emit(BaseResponse.Success(factData))
        } else {
            emit(remoteFact)
        }
    }.catch {
        val exceptionData = remoteExceptionHandler(it)
        emit(BaseResponse.Failed(exceptionData.code, exceptionData.message))
    }

    override suspend fun saveOrRemoveFactInFavoriteDataStore(fact: FactUiData) {
        factLocalDataSource.saveOrRemoveFactInFavoriteDataStore(fact)
    }

    override suspend fun getSavedFavoriteFactList(): Flow<BaseResponse<List<FactUiData>>> = flow {
        emit(factLocalDataSource.getSavedFavoriteFactList())
    }

    private suspend fun getRemoteFact(): BaseResponse<FactUiData> {
        return factRemoteDataSource.getRemoteFact()
    }

    private suspend fun getStoredFact(): BaseResponse<FactUiData> {
        return factLocalDataSource.getLocalFact()
    }

    private suspend fun storeFact(fact: FactUiData) {
        factLocalDataSource.saveFactToDataStore(fact)
    }
}
