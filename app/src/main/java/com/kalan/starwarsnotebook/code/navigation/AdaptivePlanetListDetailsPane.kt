package com.kalan.starwarsnotebook.code.navigation

import android.app.Activity
import android.widget.Toast
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kalan.starwarsnotebook.code.presentation.util.ObserveAsEvents
import com.kalan.starwarsnotebook.code.presentation.util.toString
import com.kalan.starwarsnotebook.planets.presentation.planet_details.PlanetDetailsScreen
import com.kalan.starwarsnotebook.planets.presentation.planet_list.PlanetListAction
import com.kalan.starwarsnotebook.planets.presentation.planet_list.PlanetListEvent
import com.kalan.starwarsnotebook.planets.presentation.planet_list.PlanetListViewModel
import com.kalan.starwarsnotebook.planets.presentation.planet_list.components.PlanetListScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AdaptivePlanetListDetailsPane (
    modifier: Modifier = Modifier,
    viewModel: PlanetListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    //Calculate is it phone or not?
    val activity = remember(context) { context as? Activity }
    val windowSizeClass = activity?.let { calculateWindowSizeClass(it) }
    val isPhone = windowSizeClass?.widthSizeClass == WindowWidthSizeClass.Compact

    ObserveAsEvents(events = viewModel.events) { event ->
        when(event) {
            is PlanetListEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.toString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {

                var planetClicked by remember { mutableStateOf(false) }
                PlanetListScreen(
                    state = state,
                    isPhone = isPhone,
                    onAction = { action ->
                        viewModel.onAction(action)
                        when(action) {
                            is PlanetListAction.OnPlanetClick -> {
                                planetClicked = true
                            }
                            is PlanetListAction.OnLoadNextPageIfNeeded -> {

                            }
                        }
                    }
                )
                LaunchedEffect(planetClicked) {
                    if (planetClicked) {
                        navigator.navigateTo(pane = ListDetailPaneScaffoldRole.Detail)
                        planetClicked = false
                    }
                }
            }
        },
        detailPane = {
            AnimatedPane {
                val isDetailsScreenOnly =
                    navigator.currentDestination?.pane == ListDetailPaneScaffoldRole.Detail && isPhone
                var backClicked by remember { mutableStateOf(false) }
                PlanetDetailsScreen(
                    state = state,
                    showBack = isDetailsScreenOnly,
                    onBackClick = {
                        backClicked = true
                    })
                LaunchedEffect(backClicked) {
                    if (backClicked) {
                        navigator.navigateBack()
                        backClicked = false
                    }
                }
            }
        },
        modifier = modifier
    )
}