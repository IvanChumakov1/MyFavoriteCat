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
import com.example.myfavoritecat.CatApi.ApiCats
import com.example.myfavoritecat.CatApi.ApiCatsService
import com.example.myfavoritecat.ViewModels.SearchCatsViewModel
import com.example.myfavoritecat.ViewModels.SelectingCatViewModel
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
    onNavigateBack: () -> Unit
){


    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.api-ninjas.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val catApiService = retrofit.create(ApiCatsService::class.java)

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

    ) {padding ->
        var name by remember { mutableStateOf("") }
        var cats by remember { mutableStateOf(listOf<ApiCats>()) }

        Column(
            Modifier.fillMaxWidth().padding(padding)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it },
                label = { Text("Введите имя кота")
                }
            )
            Button(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onClick = {
                    // Вызовите API и обновите список котов
                    CoroutineScope(Dispatchers.IO).launch {
                        cats = catApiService.getCatsByName(name)
                    }

                }) {
                Text("Поиск")
            }
            CatLazyRow(cats)
        }
    }
}

@Composable
fun CatLazyRow(cats: List<ApiCats>) {
    LazyColumn {
        items(cats) { cat ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(all = 8.dp)
            ) {
                Column {
                    AsyncImage(
                        model = cat.image_link,
                        contentDescription = "Cat ${cat.name}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            ,
                        alignment = Alignment.Center,

                    )
                    Text(text = cat.name, Modifier.padding(8.dp))
                }
            }
        }
    }
}



/*
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
    }*/
