package com.kalan.starwarsnotebook.planets.presentation.planet_list

import androidx.compose.runtime.Immutable
import com.kalan.starwarsnotebook.planets.presentation.models.PlanetUi

@Immutable
data class PlanetListState(
    val isLoading: Boolean = false,
    val planets: List<PlanetUi> = emptyList(),
    val selectedPlanet: PlanetUi? = null,
    val nextPageUrl: String? = null
)
