package com.kalan.starwarsnotebook.planets.domain

data class PlanetPage(
    val planets: List<Planet>,
    val nextPageUrl: String?
)

