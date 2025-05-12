package com.kalan.starwarsnotebook.planets.presentation.planet_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalan.starwarsnotebook.core.domain.util.onError
import com.kalan.starwarsnotebook.core.domain.util.onSuccess
import com.kalan.starwarsnotebook.planets.domain.PlanetsDataSource
import com.kalan.starwarsnotebook.planets.presentation.models.toUI
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlanetListViewModel(
    private val plantsDataSource: PlanetsDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(PlanetListState())

    val state = _state
        .onStart {
            loadPlanets()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            PlanetListState()
        )

    private val _events = Channel<PlanetListEvent>()
    val events = _events.receiveAsFlow()

    private var isLoadingMore = false // Prevent double fetch

    private fun loadPlanets(url: String? = null) {
        // Prevent pagination overlap
        if (isLoadingMore) return
        isLoadingMore = true
        viewModelScope.launch {
            _state.update { it.copy( isLoading = true) }
            plantsDataSource
                .getPlanets(url)
                .onSuccess { page ->
                    _state.update {
                        it.copy(isLoading = false,
                            planets = it.planets + page.planets.map { planet ->
                                planet.toUI()
                            },
                            nextPageUrl = page.nextPageUrl
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(PlanetListEvent.Error(error))
                }
            isLoadingMore = false
        }
    }

    private fun loadNextPageIfNeeded() {
        val next = _state.value.nextPageUrl
        if (!next.isNullOrBlank()) {
            loadPlanets(next)
        }
    }

    fun onAction(action: PlanetListAction) {
        when(action) {
            is PlanetListAction.OnPlanetClick -> {
                _state.update {
                    it.copy(
                        selectedPlanet = action.planetUi
                    )
                }
            }
            is PlanetListAction.OnLoadNextPageIfNeeded -> {
                loadNextPageIfNeeded()
            }
        }
    }
}