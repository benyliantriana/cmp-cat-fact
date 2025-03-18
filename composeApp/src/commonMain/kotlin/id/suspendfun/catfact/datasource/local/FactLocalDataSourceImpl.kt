package id.suspendfun.catfact.datasource.local

import id.suspendfun.catfact.base.BaseResponse
import id.suspendfun.catfact.base.exception.localExceptionHandler
import id.suspendfun.catfact.ui.fact.FactUiData
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FactLocalDataSourceImpl(
//    private val factDataStore: DataStore<FactPreference>,
//    private val favoriteFactDataStore: DataStore<FactFavoriteListPreference>,
    private val ioDispatcher: CoroutineDispatcher
) : FactLocalDataSource {
    companion object {
        private const val NO_SAVED_FACT = "No saved fact"
    }

    override suspend fun getLocalFact(): BaseResponse<FactUiData> = withContext(ioDispatcher) {
        var result: BaseResponse<FactUiData>
        try {
//            val savedFact = factDataStore.data.first()
//            result = if (savedFact.fact.isEmpty()) {
//                BaseResponse.Failed(404, NO_SAVED_FACT)
//            } else {
//                val favoriteList = getLocalFavoriteFactList()
//                val isFavorite = favoriteList.find { it.fact == savedFact.fact } != null
//                BaseResponse.Success(
//                    FactUiData(savedFact.fact, savedFact.length, isFavorite)
//                )
//            }
            result = BaseResponse.Success(FactUiData("Cat from local", 15, false))
        } catch (ioException: IOException) {
            val exceptionData = localExceptionHandler(ioException)
            result = BaseResponse.Failed(
                code = exceptionData.code,
                message = exceptionData.message
            )
        }

        return@withContext result
    }

    override suspend fun saveFactToDataStore(factData: FactUiData) {
        withContext(ioDispatcher) {
//            try {
//                factDataStore.updateData {
//                    it.copy {
//                        this.fact = factData.fact
//                        this.length = factData.length
//                        this.isFavorite = factData.isFavorite
//                    }
//                }
//            } catch (ioException: IOException) {
//                // need a better approach for this
//                throw ioException
//            }
        }
    }

    // this is so simple and lazy, but I think it's better than over-engineering
    override suspend fun saveOrRemoveFactInFavoriteDataStore(factData: FactUiData) {
        withContext(ioDispatcher) {
//            try {
//                if (factData.isFavorite) {
//                    addFavoriteFact(factData)
//                } else {
//                    removeFavoriteFact(factData)
//                }
//
//            } catch (ioException: IOException) {
//                throw ioException
//            }
        }
    }

    override suspend fun isFavoriteFact(factData: FactUiData) = withContext(ioDispatcher) {
//        val favoriteList = getLocalFavoriteFactList()
//        return@withContext favoriteList.find { it.fact == factData.fact } != null
        return@withContext false
    }

    override suspend fun getSavedFavoriteFactList(): BaseResponse<List<FactUiData>> {
//        val favoriteList: List<FactUiData> = getLocalFavoriteFactList().map {
//            FactUiData(it.fact, it.length, isFavorite = true)
//        }
        val favoriteList = listOf(
            FactUiData("cat is a cat", 15, false)
        )
        return if (favoriteList.isNotEmpty()) {
            BaseResponse.Success(favoriteList)
        } else BaseResponse.Failed(200, "Favorite list is empty")
    }
//
//    private suspend fun getLocalFavoriteFactList(): MutableList<FactPreference> =
//        withContext(ioDispatcher) {
//            return@withContext favoriteFactDataStore.data.first().factFavoriteListList.toMutableList()
//        }
//
//    private suspend fun addFavoriteFact(factData: FactUiData) {
//        withContext(ioDispatcher) {
//            val isFavoriteFact = isFavoriteFact(factData)
//            if (!isFavoriteFact) {
//                val favoriteFact = FactPreference.getDefaultInstance().copy {
//                    this.fact = factData.fact
//                    this.length = factData.length
//                    this.isFavorite = true
//                }
//
//                val favoriteList = getLocalFavoriteFactList()
//                favoriteList.add(favoriteFact)
//                favoriteFactDataStore.updateData {
//                    it.toBuilder()
//                        .clearFactFavoriteList()
//                        .addAllFactFavoriteList(favoriteList)
//                        .build()
//                }
//            }
//        }
//    }
//
//    private suspend fun removeFavoriteFact(factData: FactUiData) {
//        withContext(ioDispatcher) {
//            val favoriteList = getLocalFavoriteFactList()
//            val favoriteFact = favoriteList.find { it.fact == factData.fact }
//            favoriteFact?.let {
//                favoriteList.remove(it)
//            }
//
//            favoriteFactDataStore.updateData {
//                it.toBuilder()
//                    .clearFactFavoriteList()
//                    .addAllFactFavoriteList(favoriteList)
//                    .build()
//            }
//        }
//    }
}
