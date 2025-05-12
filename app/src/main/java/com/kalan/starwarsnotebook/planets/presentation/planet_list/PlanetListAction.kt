package com.kalan.starwarsnotebook.planets.presentation.planet_list

import com.kalan.starwarsnotebook.planets.presentation.models.PlanetUi

sealed interface PlanetListAction {
    data class OnPlanetClick(val planetUi: PlanetUi): PlanetListAction
    data class OnLoadNextPageIfNeeded(val nothing: Nothing? = null): PlanetListAction
}