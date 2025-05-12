package com.kalan.starwarsnotebook.planets.data

import com.kalan.starwarsnotebook.code.data.networking.constructUrl
import com.kalan.starwarsnotebook.code.data.networking.safeCall
import com.kalan.starwarsnotebook.core.domain.util.NetworkError
import com.kalan.starwarsnotebook.core.domain.util.Result
import com.kalan.starwarsnotebook.core.domain.util.map
import com.kalan.starwarsnotebook.planets.data.mappers.toPlanet
import com.kalan.starwarsnotebook.planets.data.networking.nao.PlanetResponseNao
import com.kalan.starwarsnotebook.planets.domain.Planet
import com.kalan.starwarsnotebook.planets.domain.PlanetPage
import com.kalan.starwarsnotebook.planets.domain.PlanetsDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemotePlanetDataSource(
    private val httpClient: HttpClient
): PlanetsDataSource {
    override suspend fun getPlanets(nextUrl: String?): Result<PlanetPage, NetworkError> {
        return safeCall<PlanetResponseNao> {
            httpClient.get (
                urlString = constructUrl(nextUrl ?: "/planets/?page=1")
            )
        }.map { response ->
            PlanetPage(
                planets = response.results.map { it.toPlanet() },
                nextPageUrl = response.next
            )
        }
    }
}