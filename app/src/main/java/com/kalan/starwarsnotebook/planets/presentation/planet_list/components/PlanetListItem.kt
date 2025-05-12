package com.kalan.starwarsnotebook.planets.presentation.planet_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kalan.starwarsnotebook.planets.domain.Planet
import com.kalan.starwarsnotebook.planets.presentation.models.PlanetUi
import com.kalan.starwarsnotebook.planets.presentation.models.toUI

@Composable
fun PlanetListItem(
    planetUi: PlanetUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (modifier = modifier
        .clickable(onClick = onClick)){
        Text(
            text = planetUi.name
        )
        Spacer(modifier = modifier
            .padding(top = 4.dp))
        AsyncImage(
            model = planetUi.imageUrl,
            contentDescription = null,
            modifier = modifier
                .padding(4.dp),
            alignment = Alignment.Center
        )
    }

}

internal  val previewPlanet = Planet(
    name = "Tatooine", climate = "arid", population = "200000",
    diameter = "", films = emptyList(), gravity = "",
    orbitalPeriod = "", residents = emptyList(), rotationPeriod = "",
    surfaceWater = "", terrain = "", url = ""
).toUI();