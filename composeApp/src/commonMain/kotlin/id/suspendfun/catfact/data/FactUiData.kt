package id.suspendfun.catfact.data

import kotlinx.serialization.Serializable

@Serializable
data class FactUiData(
    val fact: String,
    val length: Int,
    val isFavorite: Boolean
)
