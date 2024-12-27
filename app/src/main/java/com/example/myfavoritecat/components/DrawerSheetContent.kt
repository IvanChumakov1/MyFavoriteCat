package com.example.myfavoritecat.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerSheetContent(
    onSelectAll: () -> Unit,
    onDelete: () -> Unit,
    onSortAZ: () -> Unit,
    onSortZA: () -> Unit,
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()
    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Show Your Cats", modifier = Modifier.padding(bottom = 8.dp))
            Divider()
            NavigationDrawerItem(
                label = { Text("Добавить свою породу") },
                selected = false,
                onClick = {
                    // TODO: Implement adding custom breed
                    scope.launch { drawerState.close() }
                },
                icon = { Icon(Icons.Filled.Menu, contentDescription = "Add") },
                modifier = Modifier.padding(top = 8.dp)
            )
            NavigationDrawerItem(
                label = { Text("Выбрать всё") },
                selected = false,
                onClick = {
                    onSelectAll()
                    scope.launch { drawerState.close() }
                },
                icon = { Icon(Icons.Filled.Done, contentDescription = "Select All") },
                modifier = Modifier.padding(top = 8.dp)
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            NavigationDrawerItem(
                label = { Text("Сортировать по алфавиту (A-Z)") },
                selected = false,
                onClick = {
                    onSortAZ()
                    scope.launch { drawerState.close() }
                },
                icon = { Icon(Icons.Filled.Menu, contentDescription = "Sort A-Z") },
                modifier = Modifier.padding(top = 8.dp)
            )
            NavigationDrawerItem(
                label = { Text("Сортировать по алфавиту (Z-A)") },
                selected = false,
                onClick = {
                    onSortZA()
                    scope.launch { drawerState.close() }
                },
                icon = { Icon(Icons.Filled.Menu, contentDescription = "Sort Z-A") },
                modifier = Modifier.padding(top = 8.dp)
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            NavigationDrawerItem(
                label = { Text("Удалить") },
                selected = false,
                onClick = {
                    onDelete()
                    scope.launch { drawerState.close() }
                },
                icon = { Icon(Icons.Filled.Delete, contentDescription = "Delete") },
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}