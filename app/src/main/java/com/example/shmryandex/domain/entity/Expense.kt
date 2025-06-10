package com.example.shmryandex.domain.entity

data class Expense(
    val id: Int,
    val category: String,
    val amount: Int,
    val createdAt: String,
    val icon: String
) {
}