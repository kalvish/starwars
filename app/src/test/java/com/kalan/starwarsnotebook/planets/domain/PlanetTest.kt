package com.kalan.starwarsnotebook.planets.domain

import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class PlanetTest {

    private val samplePlanet = Planet(
        name = "Tatooine",
        rotationPeriod = "23",
        orbitalPeriod = "304",
        diameter = "10465",
        climate = "arid",
        gravity = "1 standard",
        terrain = "desert",
        surfaceWater = "1",
        population = "200000",
        residents = listOf("http://swapi.dev/api/people/1/"),
        films = listOf("http://swapi.dev/api/films/1/"),
        url = "http://swapi.dev/api/planets/1/"
    )

    @Test
    fun test_Planet_objectCreation_success() {
        assertEquals("Tatooine", samplePlanet.name)
        assertEquals("23", samplePlanet.rotationPeriod)
        assertEquals("1", samplePlanet.surfaceWater)
        assertEquals(listOf("http://swapi.dev/api/people/1/"), samplePlanet.residents)
    }

    @Test
    fun test_Planet_identicalObjects_success() {
        val anotherPlanet = samplePlanet.copy()
        assertEquals(samplePlanet, anotherPlanet)
    }

    @Test
    fun test_Planet_newInstanceModifiedField_success() {
        val updatedPlanet = samplePlanet.copy(name = "Naboo")
        assertEquals("Naboo", updatedPlanet.name)
        assertNotEquals(samplePlanet, updatedPlanet)
    }
}