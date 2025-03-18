package id.suspendfun.catfact.di

import id.suspendfun.catfact.ui.fact.FactViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::FactViewModel)
}
