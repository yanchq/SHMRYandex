package com.example.shmryandex.presentation.screens.expenses

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shmryandex.R
import com.example.shmryandex.presentation.navigation.AppNavGraph
import com.example.shmryandex.presentation.navigation.Screen
import com.example.shmryandex.presentation.screens.NavigationItem
import com.example.shmryandex.presentation.screens.account.AccountScreen
import com.example.shmryandex.presentation.screens.categories.CategoriesScreen
import com.example.shmryandex.presentation.screens.incomes.IncomesScreen
import com.example.shmryandex.presentation.toCurrencyString
import com.example.shmryandex.ui.theme.DividerGrey
import com.example.shmryandex.ui.theme.Grey
import com.example.shmryandex.ui.theme.PrimaryGreen
import com.example.shmryandex.ui.theme.SHMRYandexTheme
import com.example.shmryandex.ui.theme.SecondaryGreen

@Composable
fun MainScreen() {

    val navHostController = rememberNavController()

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentScreen = when (currentRoute) {
        Screen.Expenses.route -> Screen.Expenses
        Screen.Incomes.route -> Screen.Incomes
        Screen.Account.route -> Screen.Account
        Screen.Categories.route -> Screen.Categories
        Screen.Options.route -> Screen.Options
        else -> Screen.Expenses
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(currentScreen.title, currentScreen.topAppBarIcon)
        },
        bottomBar = {
            val navigateList = listOf(
                NavigationItem.Expenses,
                NavigationItem.Incomes,
                NavigationItem.Account,
                NavigationItem.Categories,
                NavigationItem.Options
            )

            NavigationBar {

                navigateList.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.screen.route,
                        onClick = {
                            navHostController.navigate(item.screen.route)
                        },
                        icon = {
                            Image(
                                painter = painterResource(item.iconId),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = item.label,
                                fontSize = 12.sp
                            )
                        }
                    )
                }
            }
        },
        floatingActionButton = {
            if (currentScreen.hasFloatingActionButton) {
                FloatingActionButton(
                    onClick = {},
                    shape = CircleShape
                ) {
                    Image(painter = painterResource(R.drawable.ic_plus), contentDescription = null)
                }
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            AppNavGraph(
                navHostController = navHostController,
                expensesScreenContent = { ExpensesScreen() },
                incomesScreenContent = { IncomesScreen() },
                accountScreenContent = { AccountScreen() },
                categoriesScreenContent = { CategoriesScreen() },
                optionsScreenContent = { IncomesScreen() },
            )
        }
    }
}

@Composable
fun ExpensesScreen(viewModel: ExpensesViewModel = hiltViewModel()) {

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
            items(uiState.value.expenses) { expense ->
                ExpenseCard(
                    R.drawable.ic_expense_type,
                    expense.category,
                    expense.amount
                )
            }
        }
    }
}

@Composable
private fun ExpenseCard(lead: Int, category: String, amount: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .border(0.7.dp, DividerGrey, RectangleShape)
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) {
        Image(
            painter = painterResource(lead),
            contentDescription = null
        )

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


@Composable
@Preview
fun Preview1() {
    SHMRYandexTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                text = "Расходы сегодня"
            )

            Icon(
                painter = painterResource(R.drawable.ic_history),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
private fun CustomTopAppBar(title: String, icon: Int?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(PrimaryGreen)
            .windowInsetsPadding(WindowInsets.statusBars)
            .height(64.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = title,
            fontSize = 22.sp
        )

        icon?.let {
            IconButton(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterEnd)

            ) {
                Image(
                    painter = painterResource(it),
                    contentDescription = null
                )
            }
        }

    }
}
