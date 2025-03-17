package id.suspendfun.catfact.ui.factdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import id.suspendfun.catfact.navigation.factdetailcomponent.FactDetailComponent

@Composable
fun ScreenB(text: String, component: FactDetailComponent) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Screen B: $text")
        Button(
            onClick = {
                component.goBack()
            }
        ) {
            Text("Go Back")
        }
    }
}
