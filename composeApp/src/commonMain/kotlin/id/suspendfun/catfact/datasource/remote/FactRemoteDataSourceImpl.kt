package id.suspendfun.catfact.datasource.remote

import id.suspendfun.catfact.base.BaseResponse
import id.suspendfun.catfact.base.exception.getDefaultRemoteException
import id.suspendfun.catfact.ui.fact.FactUiData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class FactRemoteDataSourceImpl(
//    private val factApi: FactApi,
    private val ioDispatcher: CoroutineDispatcher,
) : FactRemoteDataSource {
    override suspend fun getRemoteFact(): BaseResponse<FactUiData> = withContext(ioDispatcher) {
//        val defaultExceptionData = getDefaultRemoteException()
//        var factResponseResult: BaseResponse<FactUiData> = BaseResponse.Failed(
//            code = defaultExceptionData.code, message = defaultExceptionData.message
//        )
//        val result = factApi.getFact().awaitResponse()
//        if (result.isSuccessful) {
//            result.body()?.let {
//                factResponseResult = BaseResponse.Success(
//                    FactUiData(it.fact, it.length, false)
//                )
//            }
//            if (result.body()?.fact.isNullOrEmpty()) {
//                factResponseResult = BaseResponse.Failed(result.code(), "Fact not found!")
//            }
//        } else {
//            factResponseResult = BaseResponse.Failed(result.code(), result.message())
//        }
        val factResponseResult = BaseResponse.Success(FactUiData("Cat from remote", 15, false))
        return@withContext factResponseResult
    }
}
