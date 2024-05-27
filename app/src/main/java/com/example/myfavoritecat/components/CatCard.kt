package com.example.myfavoritecat.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.Entity.CatEntity

@Composable
fun CatCard(
    modifier: Modifier = Modifier,
    cat: CatEntity,
    onClick: () -> Unit,
    content: @Composable (() -> Unit) = {},
) {
    CatCard(
        onClick = onClick,
        year = cat.Year,
        title = cat.Title,
        imgUrl = cat.Poster,
        content = content,
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CatCard(
    modifier: Modifier = Modifier,
    imgUrl: String,
    title: String,
    year: String,
    content: @Composable (() -> Unit) = {},
    onClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(all = 8.dp)

            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Row {
            AsyncImage(
                model = imgUrl,
                contentDescription = "Cat $title",
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight(),
                alignment = Alignment.TopStart
            )
            Column(modifier = Modifier.width(250.dp).padding(horizontal = 8.dp)) {
                Text(text = title, style = MaterialTheme.typography.headlineSmall)
                Text(text = year)
            }


            Column(
                modifier = Modifier
                    .width(20.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    }
}