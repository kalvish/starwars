package com.kalan.starwarsnotebook.planets.presentation.planet_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kalan.starwarsnotebook.planets.presentation.models.PlanetUi

@Composable
fun PlanetListItem(
    planetUi: PlanetUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (modifier = modifier){
        Text(
            text = planetUi.name
        )
        Spacer(modifier = modifier
            .padding(top = 4.dp))
        AsyncImage(
            model = planetUi.imageUrl,
            contentDescription = null,
        )
    }

}