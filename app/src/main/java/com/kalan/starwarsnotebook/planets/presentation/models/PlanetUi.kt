package com.kalan.starwarsnotebook.planets.presentation.models

import com.kalan.starwarsnotebook.planets.domain.Planet

data class PlanetUi(
    val name: String,
    val climate: String,
    val terrain: String,
    val population: String,
    val imageUrl: String,
    val imageUrlHighRes: String,
    val orbitalPeriod: String,
    val gravity: String
)

fun Planet.toUI(): PlanetUi = PlanetUi(
    name = name,
    climate = climate,
    terrain = terrain,
    population = population,
    imageUrl = "https://picsum.photos/seed/${name}/400/300",
    imageUrlHighRes = "https://picsum.photos/seed/${name}/800/600",
    orbitalPeriod = orbitalPeriod,
    gravity = gravity
)
