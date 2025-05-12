package com.kalan.starwarsnotebook.planetspage

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.kalan.starwarsnotebook.planets.domain.Planet
import com.kalan.starwarsnotebook.planets.presentation.models.toUI
import com.kalan.starwarsnotebook.planets.presentation.planet_list.PlanetListState
import com.kalan.starwarsnotebook.planets.presentation.planet_details.PlanetDetailsScreen
import org.junit.Rule
import org.junit.Test

class PlanetDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

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
        residents = listOf(),
        films = listOf(),
        url = "https://swapi.dev/api/planets/1/"
    )

    @Test
    fun showsLoadingIndicator_whenLoading() {
        val state = PlanetListState(isLoading = true)

        composeTestRule.setContent {
            PlanetDetailsScreen(state = state)
        }

        composeTestRule
            .onNode(isRoot())
            .onChild()
            .assert(hasAnyAncestor(isRoot()))
            .assertExists()
    }

    @Test
    fun showsPlanetDetails_whenPlanetSelected() {
        val state = PlanetListState(
            selectedPlanet = samplePlanet.toUI(),
            isLoading = false
        )

        composeTestRule.setContent {
            PlanetDetailsScreen(state = state)
        }
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText("Tatooine").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithText("Tatooine").assertIsDisplayed()
        composeTestRule.onNodeWithText("304 day(s)").assertIsDisplayed()
    }

    @Test
    fun showsBackButton_whenShowBackIsTrue_andInvokesCallback() {
        var clicked = false
        val state = PlanetListState(selectedPlanet = samplePlanet.toUI())

        composeTestRule.setContent {
            PlanetDetailsScreen(
                state = state,
                showBack = true,
                onBackClick = { clicked = true }
            )
        }

        composeTestRule
            .onNodeWithContentDescription("Back")
            .assertIsDisplayed()
            .performClick()

        assert(clicked)
    }
}