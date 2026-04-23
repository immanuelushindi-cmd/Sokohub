package com.immanuel.sokohub.models

// ✔ Renamed from "Products" → "Product" (represents a single item)
data class Product(
    var id: String? = null,
    val name: String? = null,
    val category: String? = null,
    val brand: String? = null,
    val price: String? = null,
    val description: String? = null,
    val imageUrl: String? = null
)