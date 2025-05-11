package com.kalan.starwarsnotebook.planets.domain

import com.kalan.starwarsnotebook.core.domain.util.NetworkError
import com.kalan.starwarsnotebook.core.domain.util.Result

interface PlanetsDataSource {
    suspend fun getPlanets(): Result<List<Planet>, NetworkError>
}