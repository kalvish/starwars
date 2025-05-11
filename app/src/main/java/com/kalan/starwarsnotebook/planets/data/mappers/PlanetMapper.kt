package com.kalan.starwarsnotebook.planets.data.mappers

import com.kalan.starwarsnotebook.planets.data.networking.nao.PlanetNao
import com.kalan.starwarsnotebook.planets.domain.Planet

fun PlanetNao.toPlanet(): Planet = Planet(
    name,
    rotationPeriod,
    orbitalPeriod,
    diameter,
    climate,
    gravity,
    terrain,
    surfaceWater,
    population,
    residents,
    films,
    url
)
