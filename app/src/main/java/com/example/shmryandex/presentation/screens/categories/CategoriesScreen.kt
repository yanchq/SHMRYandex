package com.example.shmryandex.presentation.screens.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shmryandex.R
import com.example.shmryandex.ui.theme.DividerGrey
import com.example.shmryandex.ui.theme.Grey
import com.example.shmryandex.ui.theme.SecondaryGreen
import com.example.shmryandex.ui.theme.TextGrey

@Composable
fun CategoriesScreen(viewModel: CategoriesViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsState()

    Column {
        Search()

        LazyColumn {
            items(uiState.value.categoriesList) { category ->
                CategoryCard(
                    emoji = category.emoji,
                    name = category.name
                )
            }
        }
    }
}

@Composable
private fun Search() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Grey)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "Найти статью",
            fontSize = 16.sp,
            color = TextGrey
        )

        Image(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = null
        )
    }
}

@Composable
private fun CategoryCard(emoji: String, name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .border(0.7.dp, DividerGrey, RectangleShape)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier.background(SecondaryGreen, CircleShape)
        ) {
            Text(
                text = emoji
            )
        }

        Text(
            text = name,
            fontSize = 16.sp
        )
    }
}