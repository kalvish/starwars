package com.kalan.starwarsnotebook.di

import com.kalan.starwarsnotebook.code.data.networking.HttpClientFactory
import com.kalan.starwarsnotebook.code.data.networking.UnsafeEngineProvider
import com.kalan.starwarsnotebook.planets.data.RemotePlanetDataSource
import com.kalan.starwarsnotebook.planets.domain.PlanetsDataSource
import com.kalan.starwarsnotebook.planets.presentation.planet_list.PlanetListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
//        HttpClientFactory.create(CIO.create())
        HttpClientFactory.create(UnsafeEngineProvider.getUnsafeOkHttpEngine())
    }
    singleOf(::RemotePlanetDataSource).bind<PlanetsDataSource>()

    viewModel { PlanetListViewModel(get()) }
}