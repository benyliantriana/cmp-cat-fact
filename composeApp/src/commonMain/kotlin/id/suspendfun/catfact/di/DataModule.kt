package id.suspendfun.catfact.di

import id.suspendfun.catfact.datasource.local.FactLocalDataSource
import id.suspendfun.catfact.datasource.local.FactLocalDataSourceImpl
import id.suspendfun.catfact.datasource.remote.FactRemoteDataSource
import id.suspendfun.catfact.datasource.remote.FactRemoteDataSourceImpl
import id.suspendfun.catfact.repository.FactRepository
import id.suspendfun.catfact.repository.FactRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformDataModule: Module

val dataModule = module {
    singleOf(::FactLocalDataSourceImpl).bind<FactLocalDataSource>()
    singleOf(::FactRemoteDataSourceImpl).bind<FactRemoteDataSource>()
    singleOf(::FactRepositoryImpl).bind<FactRepository>()

    single {
        Dispatchers.IO
    }
}
