package com.example.shmryandex.data

import com.example.shmryandex.domain.entity.Expense
import com.example.shmryandex.domain.FinanceRepository
import com.example.shmryandex.domain.entity.Account
import com.example.shmryandex.domain.entity.Category
import com.example.shmryandex.domain.entity.Income
import javax.inject.Inject

class FinanceRepositoryImpl @Inject constructor(): FinanceRepository {

    override fun getExpensesList(): List<Expense> {
        return listOf(
            Expense(
                1,
                "Kaif",
                100000,
                "25.05.2025",
                ""
            ),
            Expense(
                1,
                "Kaif",
                100000,
                "25.05.2025",
                ""
            ),
            Expense(
                1,
                "Kaif",
                100000,
                "25.05.2025",
                ""
            )
        )
    }

    override fun getIncomesList(): List<Income> {
        return listOf(
            Income(
                1,
                "Zarplata",
                15000
            ),
            Income(
                1,
                "Zarplata",
                15000
            ),
            Income(
                1,
                "Zarplata",
                15000
            )
        )
    }

    override fun getAccount(): Account {
        return Account(
            id = 1,
            name = "Основной счет",
            balance = 1000,
            currency = "₽"
        )
    }

    override fun getCategoriesList(): List<Category> {
        return listOf(
            Category(
                id = 1,
                name = "Zarplata",
                emoji = "\uD83D\uDCB0",
                isIncome = true
            )
        )
    }
}