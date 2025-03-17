package id.suspendfun.catfact.ui.fact

import kotlinx.serialization.Serializable

@Serializable
data class FactUiData(
    val fact: String,
    val length: Int,
    val isFavorite: Boolean
)
