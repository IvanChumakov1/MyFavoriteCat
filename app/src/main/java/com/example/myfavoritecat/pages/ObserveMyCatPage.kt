package com.example.myfavoritecat.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.domain.Entity.CatEntity
import com.example.myfavoritecat.ViewModels.MyCatsViewModel
import com.example.myfavoritecat.ViewModels.ObserveSearchCatsViewModel
import com.example.myfavoritecat.ViewModels.SearchCatsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObserveMyCatPage(
    cat: CatEntity,
    onNavigateBack: () -> Unit,
    viewModel: MyCatsViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(cat.name)
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Имя кошки
            Text(
                text = cat.name,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            // Характерис��ики
            CatCharacteristic("Length", cat.length)
            CatCharacteristic("Origin", cat.origin)
            CatCharacteristic("Family Friendly", cat.family_friendly)
            CatCharacteristic("Shedding", cat.shedding)
            CatCharacteristic("General Health", cat.general_health)
            CatCharacteristic("Playfulness", cat.playfulness)
            CatCharacteristic("Meowing", cat.meowing)
            CatCharacteristic("Children Friendly", cat.children_friendly)
            CatCharacteristic("Stranger Friendly", cat.stranger_friendly)
            CatCharacteristic("Grooming", cat.grooming)
            CatCharacteristic("Intelligence", cat.intelligence)
            CatCharacteristic("Other Pets Friendly", cat.other_pets_friendly)
            CatCharacteristic("Weight", cat.max_weight)
            CatCharacteristic("Life Expectancy", cat.life_expectancy)

            // Изображение
            AsyncImage(
                model = cat.image_link,
                contentDescription = "Cat ${cat.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
        }
    }
}