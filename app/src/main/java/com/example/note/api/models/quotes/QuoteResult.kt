package com.example.note.api.models.quotes

data class QuoteResult(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<QouteEntity>,
    val totalCount: Int,
    val totalPages: Int
)
