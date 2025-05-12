package com.kalan.starwarsnotebook

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kalan.starwarsnotebook.code.navigation.AdaptivePlanetListDetailsPane
import com.kalan.starwarsnotebook.code.presentation.util.ObserveAsEvents
import com.kalan.starwarsnotebook.code.presentation.util.toString
import com.kalan.starwarsnotebook.planets.presentation.planet_details.PlanetDetailsScreen
import com.kalan.starwarsnotebook.planets.presentation.planet_list.PlanetListEvent
import com.kalan.starwarsnotebook.planets.presentation.planet_list.PlanetListViewModel
import com.kalan.starwarsnotebook.planets.presentation.planet_list.components.PlanetListScreen
import com.kalan.starwarsnotebook.ui.theme.StarWarsNoteBookTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StarWarsNoteBookTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AdaptivePlanetListDetailsPane(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}