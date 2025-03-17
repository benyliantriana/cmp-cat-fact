package id.suspendfun.catfact.ui.fact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import id.suspendfun.catfact.navigation.factcomponent.FactComponent
import id.suspendfun.catfact.navigation.factcomponent.FactEvent

@Composable
fun ScreenA(component: FactComponent) {
    val text by component.text.subscribeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Screen A")
        OutlinedTextField(
            value = text,
            onValueChange = { component.onEvent(FactEvent.UpdateText(it))},
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        )
        Button(
            onClick = {
                component.onEvent(FactEvent.NavigateToDetail)
            }
        ) {
            Text("Go to Screen B")
        }
    }
}
