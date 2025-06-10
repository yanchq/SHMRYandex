package com.example.shmryandex.presentation.screens.incomes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shmryandex.R
import com.example.shmryandex.presentation.toCurrencyString
import com.example.shmryandex.ui.theme.DividerGrey
import com.example.shmryandex.ui.theme.Grey
import com.example.shmryandex.ui.theme.SecondaryGreen

@Composable
fun IncomesScreen(viewModel: IncomesViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsState()

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(SecondaryGreen)
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Всего",
                fontSize = 16.sp
            )
            Text(
                text = uiState.value.summary.toCurrencyString(),
                fontSize = 16.sp
            )
        }

        LazyColumn {
            items(uiState.value.incomes) { expense ->
                ExpenseCard(
                    expense.category,
                    expense.amount
                )
            }
        }
    }
}

@Composable
private fun ExpenseCard(category: String, amount: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .border(0.7.dp, DividerGrey, RectangleShape)
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) {

        Text(
            modifier = Modifier
                .weight(1f),
            text = category
        )
        Text(text = amount.toCurrencyString())
        Image(
            painter = painterResource(R.drawable.ic_more),
            contentDescription = null
        )
    }
}