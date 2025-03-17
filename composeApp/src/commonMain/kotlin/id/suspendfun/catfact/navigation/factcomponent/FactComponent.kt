package id.suspendfun.catfact.navigation.factcomponent

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class FactComponent(
    componentContext: ComponentContext,
    private val onNavigateToFactDetail: (String) -> Unit,
) : ComponentContext by componentContext {
    private var _text = MutableValue("")
    val text: Value<String> = _text

    fun onEvent(event: FactEvent) {
        when (event) {
            FactEvent.NavigateToDetail -> onNavigateToFactDetail(text.value)
            is FactEvent.UpdateText -> _text.value = event.text
        }
    }
}