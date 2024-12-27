package com.example.myfavoritecat.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.Entity.CatEntity
import com.example.myfavoritecat.ViewModels.MyCatsViewModel
import com.example.myfavoritecat.ViewModels.ObserveSearchCatsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCatPage(
    catId: String?,
    onNavigateBack: () -> Unit,
    myCatsViewModel: MyCatsViewModel = hiltViewModel(),
    observeSearchCatsViewModel: ObserveSearchCatsViewModel = hiltViewModel()
) {
    val cat = if (!catId.isNullOrEmpty()) {
        observeSearchCatsViewModel.getCatById(catId)
    } else {
        null
    }

    var name by remember { mutableStateOf(cat?.name ?: "") }
    var imageLink by remember { mutableStateOf(cat?.image_link ?: "") }
    var length by remember { mutableStateOf(cat?.length ?: "") }
    var origin by remember { mutableStateOf(cat?.origin ?: "") }
    var familyFriendly by remember { mutableStateOf(cat?.family_friendly ?: "") }
    var shedding by remember { mutableStateOf(cat?.shedding ?: "") }
    var generalHealth by remember { mutableStateOf(cat?.general_health ?: "") }
    var playfulness by remember { mutableStateOf(cat?.playfulness ?: "") }
    var meowing by remember { mutableStateOf(cat?.meowing ?: "") }
    var childrenFriendly by remember { mutableStateOf(cat?.children_friendly ?: "") }
    var strangerFriendly by remember { mutableStateOf(cat?.stranger_friendly ?: "") }
    var grooming by remember { mutableStateOf(cat?.grooming ?: "") }
    var intelligence by remember { mutableStateOf(cat?.intelligence ?: "") }
    var otherPetsFriendly by remember { mutableStateOf(cat?.other_pets_friendly ?: "") }
    var maxWeight by remember { mutableStateOf(cat?.max_weight ?: "") }
    var lifeExpectancy by remember { mutableStateOf(cat?.life_expectancy ?: "") }

    fun handleSave() {
        val updatedCat = CatEntity(
            id = cat?.id ?: "",
            name = name,
            image_link = imageLink,
            length = length,
            origin = origin,
            family_friendly = familyFriendly,
            shedding = shedding,
            general_health = generalHealth,
            playfulness = playfulness,
            meowing = meowing,
            children_friendly = childrenFriendly,
            stranger_friendly = strangerFriendly,
            grooming = grooming,
            intelligence = intelligence,
            other_pets_friendly = otherPetsFriendly,
            max_weight = maxWeight,
            life_expectancy = lifeExpectancy
        )

        if (cat == null) {
            myCatsViewModel.addCat(updatedCat)
        } else {
            myCatsViewModel.updateCat(updatedCat)
        }

        onNavigateBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(if (!catId.isNullOrEmpty()) "Edit Cat" else "Add Cat")
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
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = imageLink,
                onValueChange = { imageLink = it },
                label = { Text("Image Link") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = length,
                onValueChange = { length = it },
                label = { Text("Length") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = origin,
                onValueChange = { origin = it },
                label = { Text("Origin") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = familyFriendly,
                onValueChange = { familyFriendly = it },
                label = { Text("Family Friendly") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = shedding,
                onValueChange = { shedding = it },
                label = { Text("Shedding") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = generalHealth,
                onValueChange = { generalHealth = it },
                label = { Text("General Health") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = playfulness,
                onValueChange = { playfulness = it },
                label = { Text("Playfulness") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = meowing,
                onValueChange = { meowing = it },
                label = { Text("Meowing") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = childrenFriendly,
                onValueChange = { childrenFriendly = it },
                label = { Text("Children Friendly") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = strangerFriendly,
                onValueChange = { strangerFriendly = it },
                label = { Text("Stranger Friendly") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = grooming,
                onValueChange = { grooming = it },
                label = { Text("Grooming") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = intelligence,
                onValueChange = { intelligence = it },
                label = { Text("Intelligence") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = otherPetsFriendly,
                onValueChange = { otherPetsFriendly = it },
                label = { Text("Other Pets Friendly") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = maxWeight,
                onValueChange = { maxWeight = it },
                label = { Text("Max Weight") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = lifeExpectancy,
                onValueChange = { lifeExpectancy = it },
                label = { Text("Life Expectancy") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { handleSave() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(if (!catId.isNullOrEmpty()) "Save Changes" else "Add Cat")
            }
        }
    }
}