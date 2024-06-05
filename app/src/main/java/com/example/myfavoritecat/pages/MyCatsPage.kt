package com.example.myfavoritecat.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.Entity.CatEntity
import com.example.myfavoritecat.R
import com.example.myfavoritecat.ViewModels.MyCatsViewModel
import com.example.myfavoritecat.components.CatCard
import com.example.myfavoritecat.components.IconWithText

@SuppressLint("FlowOperatorInvokedInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCatsPage(
    onNavigationToSearchCatsPage: () -> Unit,
    viewModel: MyCatsViewModel = hiltViewModel()
) {
    val cats = viewModel.cats.collectAsState(initial = emptyList())
    val selectedCats = remember {
        mutableStateListOf<CatEntity>()
    }

    fun isSelected(cat: CatEntity): Boolean {
        val index = selectedCats.indexOf(cat)
        return index != -1
    }

    fun toggleSelect(cat: CatEntity) {
        val index = selectedCats.indexOf(cat)
        if (index == -1) {
            selectedCats.add(cat)
        } else {
            selectedCats.removeAt(index)
        }
    }

    fun handleDelete() {
        selectedCats.forEach { viewModel.removeCat(it) }
        selectedCats.clear()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigationToSearchCatsPage() }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = { handleDelete() }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Localized description",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text("Cats to Watch")
                },
            )
        },
        contentWindowInsets = WindowInsets(left = 8.dp, right = 8.dp)
    ) { padding ->
        if (cats.value.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                IconWithText(
                    imageVector = ImageVector.vectorResource(R.drawable.black_cat_icon_177458),
                    text = "There are currently no cats in your watch list. Tap the button below to get started!"
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(padding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(cats.value.size) { index ->
                    val cat = cats.value[index]

                    CatCard(
                        cat = cat,
                        onClick = { toggleSelect(cat) },
                    ) {
                        Checkbox(
                            checked = isSelected(cat),
                            onCheckedChange = { toggleSelect(cat) })
                    }
                }
            }
        }
    }
}
