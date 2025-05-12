package com.kalan.starwarsnotebook.planets.presentation.planet_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kalan.starwarsnotebook.planets.domain.Planet
import com.kalan.starwarsnotebook.planets.presentation.models.PlanetUi
import com.kalan.starwarsnotebook.planets.presentation.models.toUI

@Composable
fun PlanetListItem(
    planetUi: PlanetUi,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
    } else {
        MaterialTheme.colorScheme.surface
    }
    Card (modifier = Modifier
        .padding(8.dp)
        .shadow(
            elevation = 15.dp,
            shape = RectangleShape,
            ambientColor = MaterialTheme.colorScheme.primary,
            spotColor = MaterialTheme.colorScheme.primary
        ),
        shape = RectangleShape,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ){
        Column (modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .clickable(onClick = onClick),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                text = planetUi.name,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier
                .padding(top = 4.dp))
            Text(
                text = planetUi.climate
            )
            Spacer(modifier = Modifier
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
}

internal  val previewPlanet = Planet(
    name = "Tatooine", climate = "arid", population = "200000",
    diameter = "", films = emptyList(), gravity = "",
    orbitalPeriod = "", residents = emptyList(), rotationPeriod = "",
    surfaceWater = "", terrain = "", url = ""
).toUI();