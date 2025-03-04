@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.homeMenu.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homeMenu.R
import com.example.homeMenu.model.Dish
import com.example.homeMenu.ui.AppViewModelProvider
import com.example.homeMenu.ui.components.DishRatingIcon
import com.example.homeMenu.ui.components.FavoriteButton
import com.example.homeMenu.ui.components.HeaderText
import com.example.homeMenu.ui.components.ImageLoader
import com.example.homeMenu.ui.components.RatingDialog
import com.example.homeMenu.utils.DishSharedElementKey
import com.example.homeMenu.utils.DishSharedElementType
import com.example.homeMenu.viewModel.BaseDishViewModel

fun <T> spatialExpressiveSpring() =
    spring<T>(
        dampingRatio = 0.8f,
        stiffness = 380f,
    )

fun <T> nonSpatialExpressiveSpring() =
    spring<T>(
        dampingRatio = 1f,
        stiffness = 1600f,
    )

val dishDetailBoundsTransform =
    BoundsTransform { _, _ ->
        spatialExpressiveSpring()
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishDetailScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    paddingValues: PaddingValues,
    dish: Dish,
    viewModel: BaseDishViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onPressBack: () -> Unit,
) {
    val updatedDish by viewModel.getDishById(dish.id).collectAsState(initial = dish)

    val backgroundTint by animateColorAsState(
        targetValue = if (updatedDish!!.isFavorite) MaterialTheme.colorScheme.surfaceContainerHigh else MaterialTheme.colorScheme.surfaceContainerLow,
    )

    val iconTint by animateColorAsState(
        targetValue = if (updatedDish!!.isFavorite) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant,
    )

    with(sharedTransitionScope) {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier =
                        Modifier.renderInSharedTransitionScopeOverlay(
                            zIndexInOverlay = 1f,
                        ),
                    title = {},
                    navigationIcon = {
                        IconButton(
                            onClick = { onPressBack() },
                            modifier =
                                Modifier
                                    .size(48.dp),
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Go Back",
                                modifier = Modifier.size(24.dp),
                            )
                        }
                    },
                    actions = {
                        FavoriteButton(
                            modifier =
                                Modifier
                                    .clip(CircleShape)
                                    .size(48.dp)
                                    .background(backgroundTint),
                            onFavoriteClick = { viewModel.toggleFavorite(dish.id) },
                            updatedDish = updatedDish,
                            iconTint = iconTint,
                        )
                    },
                    colors =
                        TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                )
            },
        ) {
            val roundedCornerAnim by animatedContentScope.transition
                .animateDp(label = "rounded corner") { enterExit: EnterExitState ->
                    when (enterExit) {
                        EnterExitState.PreEnter -> 20.dp
                        EnterExitState.Visible -> 0.dp
                        EnterExitState.PostExit -> 20.dp
                    }
                }

            with(sharedTransitionScope) {
                Box(
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxSize()
                            .sharedBounds(
                                rememberSharedContentState(
                                    key =
                                        DishSharedElementKey(
                                            dishId = dish.id,
                                            origin = dish.name,
                                            type = DishSharedElementType.Bounds,
                                        ),
                                ),
                                animatedContentScope,
                                clipInOverlayDuringTransition =
                                    OverlayClip(RoundedCornerShape(roundedCornerAnim)),
                                boundsTransform = dishDetailBoundsTransform,
                                exit = fadeOut(nonSpatialExpressiveSpring()),
                                enter = fadeIn(nonSpatialExpressiveSpring()),
                            )
                            .padding(
                                top = it.calculateTopPadding(),
                                bottom = paddingValues.calculateBottomPadding(),
                            ),
                ) {
                    BasicDishInfo(
                        sharedTransitionScope = sharedTransitionScope,
                        animatedContentScope = animatedContentScope,
                        dish = dish,
                        updateDish = updatedDish,
                    ) { rating ->
                        viewModel.toggleRating(dish.id, rating)
                    }
                }
            }
        }
    }
}

@Composable
fun BasicDishInfo(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    dish: Dish,
    updateDish: Dish?,
    onRatingChanged: (Int) -> Unit,
) {
    with(sharedTransitionScope) {
        val roundedCornerAnimation by animatedContentScope.transition
            .animateDp(label = "rounded corner") { enterExit: EnterExitState ->
                when (enterExit) {
                    EnterExitState.PreEnter -> 0.dp
                    EnterExitState.Visible -> 20.dp
                    EnterExitState.PostExit -> 20.dp
                }
            }

        Column(
            modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderText(
                text = dish.name,
                style = MaterialTheme.typography.titleLarge,
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .sharedBounds(
                            rememberSharedContentState(
                                key =
                                    DishSharedElementKey(
                                        dishId = dish.id,
                                        origin = dish.name,
                                        type = DishSharedElementType.Title,
                                    ),
                            ),
                            animatedVisibilityScope = animatedContentScope,
                            enter = fadeIn(nonSpatialExpressiveSpring()),
                            exit = fadeOut(nonSpatialExpressiveSpring()),
                            resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds(),
                            boundsTransform = dishDetailBoundsTransform,
                        ),
                textColor = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.height(24.dp))
            SecondDishInfo(
                updateDish,
                onRatingChanged,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier =
                    Modifier
                        .size(200.dp, 200.dp)
                        .background(MaterialTheme.colorScheme.background),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
            ) {
                ImageLoader(
                    dish.imageUrl,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .sharedBounds(
                                rememberSharedContentState(
                                    key =
                                        DishSharedElementKey(
                                            dishId = dish.id,
                                            origin = dish.name,
                                            type = DishSharedElementType.Image,
                                        ),
                                ),
                                animatedVisibilityScope = animatedContentScope,
                                boundsTransform = dishDetailBoundsTransform,
                            )
                            .clip(shape = RoundedCornerShape(10.dp)),
                )
            }
            Spacer(modifier = Modifier.weight(2f))
            OpenLinkButton(
                modifier =
                    Modifier
                        .wrapContentSize(Alignment.BottomCenter)
                        .size(180.dp, 50.dp),
                dish = dish,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun OpenLinkButton(
    modifier: Modifier = Modifier,
    dish: Dish,
) {
    val context = LocalContext.current

    val url = dish.recipeLink

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

    Button(
        onClick = {
            context.startActivity(intent)
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
    ) {
        Text(
            text = stringResource(R.string.recipe),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun SecondDishInfo(
    updateDish: Dish?,
    onRatingChanged: (Int) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .size(400.dp, 70.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        CookedPrepTime()
        Feedback(updateDish, onRatingChanged)
    }
}

@Composable
fun Feedback(
    updatedDish: Dish?,
    onRatingChanged: (Int) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedRating by remember { mutableStateOf(updatedDish?.rating ?: 0) }

    Card(
        modifier =
            Modifier
                .size(185.dp, 70.dp)
                .background(MaterialTheme.colorScheme.background)
                .wrapContentWidth(align = Alignment.End)
                .padding(end = 25.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
    ) {
        HeaderText(
            text = stringResource(R.string.your_rating),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textColor = MaterialTheme.colorScheme.onSurface,
        )
        if (selectedRating == 0) {
            Button(
                onClick = { showDialog = true },
                modifier = Modifier.padding(2.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondaryContainer),
                elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
            ) {
                Text(
                    text = stringResource(R.string.rate),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
            if (showDialog) {
                RatingDialog(
                    currentRating = selectedRating,
                    onRatingSelected = { rating ->
                        selectedRating = rating
                        onRatingChanged(rating)
                        showDialog = false
                    },
                    onDismiss = { showDialog = false },
                )
            }
        } else {
            DishRatingIcon(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(48.dp),
                backgroundColorIcon = MaterialTheme.colorScheme.surface,
                updatedDish = updatedDish,
                onRatingChanged = onRatingChanged,
            )
        }
    }
}

@Composable
fun CookedPrepTime() {
    Box(
        modifier =
            Modifier
                .size(185.dp, 70.dp)
                .wrapContentWidth(Alignment.Start),
    ) {
        HeaderText(
            text = stringResource(R.string.cooked_time),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally),
            textColor = MaterialTheme.colorScheme.onSurface,
        )
        Row(
            modifier =
                Modifier
                    .padding(top = 25.dp)
                    .size(185.dp, 50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter =
                    painterResource(
                        id = R.drawable.free_icon_clock_7164560,
                        // Эта обложка была разработана с использованием ресурсов сайта Flaticon.com.
                    ),
                contentDescription = null,
                modifier =
                    Modifier
                        .size(24.dp)
                        .background(MaterialTheme.colorScheme.background),
                tint = MaterialTheme.colorScheme.secondary,
            )

            Text(
                // dish.time
                text = "30 мин.",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier =
                    Modifier
                        .wrapContentSize(align = Alignment.CenterStart)
                        .padding(start = 8.dp),
            )
        }
    }
}

// @Preview("default")
// @Preview("dark theme", uiMode = UI_MODE_NIGHT_YES)
// @Preview("large font", fontScale = 2f)
// @Composable
// private fun DishDetailPreview() {
//    val viewModel = DishViewModel()
//    val dish = dishes.first()
//
//    PreviewWrapper {
//        val paddingValues = PaddingValues(16.dp)
//        DishDetailScreen(
//            paddingValues = paddingValues,
//            dish = dish,
//            viewModel = viewModel,
//            onPressBack = {},
//        )
//    }
// }
