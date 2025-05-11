package com.kalan.starwarsnotebook.planets.data.networking.nao

import kotlinx.serialization.Serializable

@Serializable
data class PlanetResponseNao(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PlanetNao>
)
