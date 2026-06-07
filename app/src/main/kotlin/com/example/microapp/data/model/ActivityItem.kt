package com.example.microapp.data.model

data class ActivityItem(
    val icon: String,
    val title: String,
    val time: String,
    val amount: String,
    val isNegative: Boolean = false
)
