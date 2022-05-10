package com.example.core.entity

@Parcelize
data class Receipe(
    val id: Int? = null,
    val title: String? = null,
    val publisher: String? = null,
    val image: String? = null,
    val rating: Int? = null,
    val sourceUrl: String? = null,
    val description: String? = null,
    val cookingInstruction: String? = null,
    val dateAdded: String? = null,
    val dateUpdated: String? = null,
    val ingredients: List<String>? = listOf(),
) : Parcelable