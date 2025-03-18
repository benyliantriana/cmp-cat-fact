package id.suspendfun.catfact.datasource.remote

import id.suspendfun.catfact.base.BaseResponse
import id.suspendfun.catfact.ui.fact.FactUiData

interface FactRemoteDataSource {
    suspend fun getRemoteFact(): BaseResponse<FactUiData>
}
