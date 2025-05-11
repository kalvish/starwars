package com.kalan.starwarsnotebook.planets.presentation.planet_list

import com.kalan.starwarsnotebook.core.domain.util.NetworkError

sealed interface PlanetListEvent {
    data class Error(val error: NetworkError): PlanetListEvent
}