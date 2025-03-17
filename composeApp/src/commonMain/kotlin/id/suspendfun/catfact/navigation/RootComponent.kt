package id.suspendfun.catfact.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import id.suspendfun.catfact.navigation.factcomponent.FactComponent
import id.suspendfun.catfact.navigation.factdetailcomponent.FactDetailComponent
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()
    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.FactScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        configuration: Configuration,
        context: ComponentContext,
    ): Child {
        return when (configuration) {
            Configuration.FactScreen -> Child.FactScreen(
                FactComponent(
                    componentContext = context,
                    onNavigateToFactDetail = { text ->
                        navigation.pushNew(Configuration.FactDetailScreen(text))
                    }
                )
            )

            is Configuration.FactDetailScreen -> Child.FactDetailScreen(
                FactDetailComponent(
                    text = configuration.text,
                    componentContext = context,
                    onGoBack = {
                        navigation.pop()
                    }
                )
            )
        }
    }

    sealed class Child {
        data class FactScreen(val component: FactComponent) : Child()
        data class FactDetailScreen(val component: FactDetailComponent) : Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object FactScreen : Configuration()

        @Serializable
        data class FactDetailScreen(val text: String) : Configuration()
    }
}
