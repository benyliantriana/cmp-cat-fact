package id.suspendfun.catfact.ui.fact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import catfact.composeapp.generated.resources.Res
import catfact.composeapp.generated.resources.fact_favorite_list
import catfact.composeapp.generated.resources.fact_length
import catfact.composeapp.generated.resources.fact_like
import catfact.composeapp.generated.resources.fact_liked
import catfact.composeapp.generated.resources.fact_multiple_cats
import catfact.composeapp.generated.resources.fact_share
import catfact.composeapp.generated.resources.fact_title
import catfact.composeapp.generated.resources.fact_update_fact
import catfact.composeapp.generated.resources.loading
import coil3.compose.AsyncImage
import id.suspendfun.catfact.component.ButtonText
import id.suspendfun.catfact.component.IconButtonWithLabel
import id.suspendfun.catfact.component.TextBody
import id.suspendfun.catfact.component.TextBodyBold
import id.suspendfun.catfact.component.TextTitle
import id.suspendfun.catfact.navigation.factcomponent.FactComponent
import id.suspendfun.catfact.navigation.factcomponent.FactEvent
import id.suspendfun.catfact.theme.AppColors
import id.suspendfun.catfact.util.catImageUrl
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FactScreen(
    component: FactComponent,
    viewModel: FactViewModel = koinViewModel(),
) {
    val factUiState = viewModel.factUiState.collectAsStateWithLifecycle().value
    val currentFact = viewModel.currentFactResponse.collectAsStateWithLifecycle().value
    val hasMultipleCats = viewModel.hasMultipleCats.collectAsStateWithLifecycle().value
    val isUpdateButtonEnabled = factUiState !is FactUiState.Loading

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState = lifecycleOwner.lifecycle.currentStateFlow.collectAsState().value

    LaunchedEffect(lifecycleState) {
        if (lifecycleState == Lifecycle.State.RESUMED) {
            // to update like state
//            viewModel.getSavedFact()
        }
    }
    Scaffold(
        modifier = Modifier
            .background(AppColors.Saffron)
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) { innerPadding ->
        PortraitView(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            factUiState = factUiState,
            hasMultipleCats = hasMultipleCats,
            currentFact = currentFact,
            isUpdateButtonEnabled = isUpdateButtonEnabled,
            updateFact = {
                viewModel.updateFact()
            },
            saveFactToFavorite = {

            },
            navigateToFavoriteScreen = { component.onEvent(FactEvent.NavigateToDetail) }
        )
    }
}

@Composable
private fun PortraitView(
    modifier: Modifier = Modifier,
    factUiState: FactUiState,
    hasMultipleCats: Boolean,
    currentFact: FactUiData,
    isUpdateButtonEnabled: Boolean,
    updateFact: () -> Unit,
    saveFactToFavorite: (FactUiData) -> Unit,
    navigateToFavoriteScreen: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(AppColors.Saffron)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(100.dp))
        CatImage()
        FactView(
            factUiState = factUiState,
            hasMultipleCats = hasMultipleCats,
            currentFact = currentFact,
            isUpdateButtonEnabled = isUpdateButtonEnabled,
            updateFact = updateFact,
            saveFactToFavorite = saveFactToFavorite,
            navigateToFavoriteScreen = navigateToFavoriteScreen,
        )
    }
}

@Composable
private fun FactView(
    factUiState: FactUiState,
    hasMultipleCats: Boolean,
    currentFact: FactUiData,
    isUpdateButtonEnabled: Boolean,
    updateFact: () -> Unit,
    saveFactToFavorite: (FactUiData) -> Unit,
    navigateToFavoriteScreen: () -> Unit,
) {
    Spacer(Modifier.height(20.dp))
    Title()
    Spacer(Modifier.height(20.dp))
    MultipleCat(hasMultipleCats)
    Fact(factUiState, currentFact)
    FactLength(factUiState, currentFact)
    Spacer(Modifier.height(10.dp))
    FactSaveAndShareButton(factUiState, currentFact, saveFactToFavorite)
    Spacer(Modifier.height(10.dp))
    FactUpdateButton(isUpdateButtonEnabled) {
        updateFact()
    }
    FactError(factUiState)
    FactFavoriteButton {
        navigateToFavoriteScreen()
    }
}

@Composable
private fun Title() {
    TextTitle(stringResource(Res.string.fact_title))
}

@Composable
private fun MultipleCat(hasMultipleCats: Boolean) {
    if (hasMultipleCats) {
        TextBodyBold(stringResource(Res.string.fact_multiple_cats))
        Spacer(Modifier.height(20.dp))
    }
}

@Composable
private fun Fact(factUiState: FactUiState, currentFact: FactUiData) {
    val factDescription = when (factUiState) {
        is FactUiState.Success -> factUiState.factUiData.fact
        else -> currentFact.fact
    }
    if (factDescription.isNotEmpty()) {
        TextBody(factDescription)
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun FactLength(factUiState: FactUiState, currentFact: FactUiData) {
    val length = when (factUiState) {
        is FactUiState.Success -> factUiState.factUiData.length
        else -> currentFact.fact.length
    }
    if (length > 100) {
        TextBodyBold(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.fact_length, length),
            textAlign = TextAlign.Right
        )
    }
}

@Composable
private fun FactSaveAndShareButton(
    factUiState: FactUiState,
    currentFact: FactUiData,
    saveFactToFavorite: (FactUiData) -> Unit,
) {
    val factData = when (factUiState) {
        is FactUiState.Success -> factUiState.factUiData
        else -> currentFact
    }
    if (factData.fact.isNotEmpty()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f))
            if (factData.isFavorite) {
                IconButtonWithLabel(
                    icon = Icons.Filled.Favorite,
                    label = stringResource(Res.string.fact_liked),
                    tintColor = AppColors.LightRed
                ) {
                    saveFactToFavorite(factData)
                }
            } else {
                IconButtonWithLabel(
                    icon = Icons.Filled.FavoriteBorder,
                    label = stringResource(Res.string.fact_like)
                ) {
                    saveFactToFavorite(factData)
                }
            }
            Spacer(Modifier.width(8.dp))
            IconButtonWithLabel(
                icon = Icons.Filled.Share,
                label = stringResource(Res.string.fact_share)
            ) {
//                context.shareFact(factData.fact)
            }
        }
    }
}

@Composable
private fun FactUpdateButton(
    isUpdateButtonEnabled: Boolean,
    onUpdateClick: () -> Unit,
) {
    val updateButtonText = if (isUpdateButtonEnabled) {
        stringResource(Res.string.fact_update_fact)
    } else {
        stringResource(Res.string.loading)
    }
    ButtonText(
        textButton = updateButtonText,
        isEnabled = isUpdateButtonEnabled,
    ) {
        onUpdateClick()
    }
}

@Composable
private fun FactError(factUiState: FactUiState) {
    val textButton = if (factUiState is FactUiState.Failed) {
        factUiState.message
    } else ""
    TextBody(
        text = textButton,
        color = AppColors.LightRed,
    )
    if (textButton.isNotEmpty()) {
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun CatImage(
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        modifier = modifier.fillMaxWidth(),
        model = catImageUrl,
        contentDescription = null,
    )
}

@Composable
private fun FactFavoriteButton(
    navigateToFavoriteScreen: () -> Unit,
) {
    ButtonText(stringResource(Res.string.fact_favorite_list)) {
        navigateToFavoriteScreen()
    }
}
