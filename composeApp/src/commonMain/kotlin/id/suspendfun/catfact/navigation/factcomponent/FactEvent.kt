package id.suspendfun.catfact.navigation.factcomponent

sealed interface FactEvent {
    data object NavigateToDetail: FactEvent
    data class UpdateText(val text: String): FactEvent
}
