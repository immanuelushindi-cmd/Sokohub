package com.immanuel.sokohub.models

data class User(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val uid: String = "",
    val role: String = "user"
)
