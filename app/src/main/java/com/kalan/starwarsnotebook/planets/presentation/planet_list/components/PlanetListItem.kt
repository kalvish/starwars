package com.kalan.starwarsnotebook.planets.presentation.planet_list.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kalan.starwarsnotebook.planets.presentation.models.PlanetUi

@Composable
fun PlanetListItem(
    planetUi: PlanetUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = planetUi.name
    )
}