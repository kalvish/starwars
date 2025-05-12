package com.kalan.starwarsnotebook.planets.presentation.planet_details

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kalan.starwarsnotebook.planets.presentation.planet_list.PlanetListState
import com.kalan.starwarsnotebook.planets.presentation.planet_list.components.previewPlanet
import com.kalan.starwarsnotebook.ui.theme.StarWarsNoteBookTheme

@Composable
fun PlanetDetailsScreen(
    state: PlanetListState,
    modifier: Modifier = Modifier
) {
    val contentColor = if(isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }
    if(state.isLoading) {
        Box (
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if(state.selectedPlanet !=null){
        val planet = state.selectedPlanet
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = planet.name,
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                color = contentColor
            )
            Spacer(modifier = modifier
                .padding(8.dp))
            Text(
                text = "${planet.orbitalPeriod} day(s)",
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                color = contentColor
            )
            Spacer(modifier = modifier
                .padding(8.dp))
            Text(
                text = " ${planet.gravity} m/s^2",
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                color = contentColor
            )
        }
    }
}

@Preview
@Composable
fun PlanetDetailsScreenPreview() {
    StarWarsNoteBookTheme {
        PlanetDetailsScreen(
            state = PlanetListState (
                selectedPlanet = previewPlanet
            ),
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }
}