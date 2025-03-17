package id.suspendfun.catfact.navigation.factdetailcomponent

import com.arkivanov.decompose.ComponentContext

class FactDetailComponent(
    val text: String,
    componentContext: ComponentContext,
    private val onGoBack: () -> Unit
): ComponentContext by componentContext {
    fun goBack() {
        onGoBack()
    }
}
