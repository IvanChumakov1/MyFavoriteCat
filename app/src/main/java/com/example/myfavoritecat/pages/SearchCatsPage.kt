package com.example.myfavoritecat.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.domain.Entity.CatEntity
import com.example.myfavoritecat.ViewModels.ObserveSearchCatsViewModel
import com.example.myfavoritecat.ViewModels.SearchCatsViewModel
import com.example.myfavoritecat.components.CatCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCatsPage(
    onNavigateBack: () -> Unit,
    onNavigateToObserveCatPage: (cat: CatEntity) -> Unit,
    observeSearchCatsViewModel: ObserveSearchCatsViewModel = hiltViewModel()
) {
    val cats = observeSearchCatsViewModel.searchedCats
    val searchQuery = observeSearchCatsViewModel.searchQuery
    var isResult by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = searchQuery.value) {
        if (searchQuery.value.isNotEmpty()) {
            isResult = true
            observeSearchCatsViewModel.searchCats(searchQuery.value)
        }
    }

    fun handleSearch() {
        isResult = true
        observeSearchCatsViewModel.searchCats(searchQuery.value)
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
                value = searchQuery.value,
                onValueChange = {
                    observeSearchCatsViewModel.searchCats(it)
                },
                label = { Text(text = "Cat Title") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(5.dp),
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { handleSearch() }, enabled = searchQuery.value.isNotEmpty(), modifier = Modifier.fillMaxWidth().padding(5.dp)) {
                    Text(text = "Search")
                }
            }
            if (isResult) {
                if (cats.value != null) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        items(cats.value!!) { cat ->
                            CatCard(
                                cat = cat,
                                onClick = {
                                    onNavigateToObserveCatPage(cat)
                                }
                            ) {
                            }
                        }
                    }
                }
            }
        }
    }
}

