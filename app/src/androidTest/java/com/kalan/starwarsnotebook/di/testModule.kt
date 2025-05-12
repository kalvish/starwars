package com.kalan.starwarsnotebook.di

import com.kalan.starwarsnotebook.planets.domain.PlanetsDataSource
import com.kalan.starwarsnotebook.planets.presentation.planet_list.PlanetListViewModel
import com.kalan.starwarsnotebook.planetspage.FakePlanetDataSource
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val testModule = module {
    single<PlanetsDataSource> { FakePlanetDataSource() }
    viewModel { PlanetListViewModel(plantsDataSource = get()) }
}