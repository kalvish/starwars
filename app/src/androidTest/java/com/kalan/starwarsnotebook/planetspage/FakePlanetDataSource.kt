package com.kalan.starwarsnotebook.planetspage

import com.kalan.starwarsnotebook.core.domain.util.NetworkError
import com.kalan.starwarsnotebook.core.domain.util.Result
import com.kalan.starwarsnotebook.planets.domain.Planet
import com.kalan.starwarsnotebook.planets.domain.PlanetPage
import com.kalan.starwarsnotebook.planets.domain.PlanetsDataSource

class FakePlanetDataSource(
    private val shouldReturnError: Boolean = false) : PlanetsDataSource{
    override suspend fun getPlanets(nextUrl: String?): Result<PlanetPage, NetworkError> {
        return if (shouldReturnError) {
            Result.Error(NetworkError.SERVER_ERROR)
        } else {
            val fakePlanets = listOf(
                Planet(
                    name = "Tatooine", climate = "arid", population = "200000",
                    diameter = "", films = emptyList(), gravity = "9.8",
                    orbitalPeriod = "365", residents = emptyList(), rotationPeriod = "",
                    surfaceWater = "", terrain = "", url = ""
                ),
                Planet(
                    name = "Naboo", climate = "temperate", population = "4500000000",
                    diameter = "", films = emptyList(), gravity = "4.5",
                    orbitalPeriod = "45", residents = emptyList(), rotationPeriod = "",
                    surfaceWater = "", terrain = "", url = "")
            )
            val fakePlanetPage = PlanetPage (
                planets = fakePlanets,
                nextPageUrl = null
            )
            return Result.Success(fakePlanetPage)
        }

    }
}
