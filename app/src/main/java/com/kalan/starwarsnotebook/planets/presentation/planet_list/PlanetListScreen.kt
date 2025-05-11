package com.kalan.starwarsnotebook.planets.presentation.planet_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kalan.starwarsnotebook.planets.presentation.planet_list.PlanetListState

@Composable
fun PlanetListScreen(
    state: PlanetListState,
    modifier: Modifier = Modifier
){
    if(state.isLoading) {
        Box (
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn (modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSecondary),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(state.planets) { planetUi ->
                PlanetListItem(
                    planetUi = planetUi,
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }
}