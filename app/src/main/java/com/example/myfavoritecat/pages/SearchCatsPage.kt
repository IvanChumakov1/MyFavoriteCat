package com.example.myfavoritecat.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.myfavoritecat.ViewModels.SearchCatsViewModel
import com.example.myfavoritecat.ViewModels.SelectingCatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCatsPage(
    onNavigateBack: () -> Unit,
    onNavigateToObserveSearchCatsPage: (name: String) -> Unit,
    selectingViewModel: SelectingCatViewModel = hiltViewModel(),
    viewModel: SearchCatsViewModel = hiltViewModel()
) {
    val selectedCat by selectingViewModel.selectedCat.collectAsState()

    val (name, setTitle) = rememberSaveable {
        mutableStateOf("")
    }


    fun handleSearch() {
        onNavigateToObserveSearchCatsPage(name)
    }

    fun handleAdd() {
        if (selectedCat !== null) {
            viewModel.addCat(selectedCat!!)
            selectingViewModel.clear()
            onNavigateBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text("Add Cat")
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        contentWindowInsets = WindowInsets(left = 8.dp, right = 8.dp)
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
        ) {
            OutlinedTextField(
                value = selectedCat?.name ?: name,
                onValueChange = setTitle,
                label = { Text(text = "Cat Title") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                readOnly = selectedCat !== null
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { handleAdd() },
                    enabled = selectedCat !== null
                ) {
                    Text(text = "Add")
                }

                Button(onClick = { selectingViewModel.clear() }, enabled = selectedCat !== null) {
                    Text(text = "Clear")
                }

                Button(onClick = { handleSearch() }, enabled = name.isNotEmpty()) {
                    Text(text = "Search")
                }
            }

            if (selectedCat != null) {
                AsyncImage(
                    model = selectedCat!!.image_link,
                    contentDescription = "Cat ${selectedCat!!.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(vertical = 16.dp),
                    alignment = Alignment.Center
                )
            }
        }
    }

}