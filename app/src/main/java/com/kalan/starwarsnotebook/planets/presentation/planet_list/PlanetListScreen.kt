package com.kalan.starwarsnotebook.planets.presentation.planet_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kalan.starwarsnotebook.planets.presentation.planet_list.PlanetListAction
import com.kalan.starwarsnotebook.planets.presentation.planet_list.PlanetListState
import com.kalan.starwarsnotebook.planets.presentation.planet_list.PlanetListViewModel

@Composable
fun PlanetListScreen(
    state: PlanetListState,
    onAction: (PlanetListAction) -> Unit,
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
            .background(MaterialTheme.colorScheme.onSecondary)
            .testTag("PlanetList"),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {

            itemsIndexed(state.planets) { index, planetUi ->
                if (index >= state.planets.size -1) {
                    LaunchedEffect(Unit) {
                        onAction(PlanetListAction.OnLoadNextPageIfNeeded(null))
                    }
                }

                PlanetListItem(
                    planetUi = planetUi,
                    onClick = {
                        onAction(PlanetListAction.OnPlanetClick(planetUi))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }

            if (state.nextPageUrl !=null) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}