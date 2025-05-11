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

    private fun loadPlanets() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            plantsDataSource
                .getPlanets()
                .onSuccess { planets ->
                    _state.update {
                        it.copy(isLoading = false,
                            planets = planets.map { planet ->
                                planet.toUI()
                            })
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    _events.send(PlanetListEvent.Error(error))
                }
        }
    }
}