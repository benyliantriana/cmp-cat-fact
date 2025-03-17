package id.suspendfun.catfact.main

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import id.suspendfun.catfact.navigation.RootComponent
import id.suspendfun.catfact.ui.fact.ScreenA
import id.suspendfun.catfact.ui.factdetail.ScreenB

@Composable
@Preview
fun App(root: RootComponent) {
    MaterialTheme {
        val childStack by root.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) { child ->
            when(val instance = child.instance) {
                is RootComponent.Child.FactScreen -> ScreenA(instance.component)
                is RootComponent.Child.FactDetailScreen -> ScreenB(
                    instance.component.text,
                    instance.component
                )
            }
        }
    }
}
