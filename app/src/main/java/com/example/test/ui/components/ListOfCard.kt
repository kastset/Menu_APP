@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.test.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.R
import com.example.test.model.Dish
import com.example.test.ui.screens.dishDetailBoundsTransform
import com.example.test.ui.screens.nonSpatialExpressiveSpring
import com.example.test.utils.DishSharedElementKey
import com.example.test.utils.DishSharedElementType
import kotlinx.coroutines.flow.Flow

@Composable
fun ListOfCard(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope,
    onDishClick: (Dish) -> Unit,
    dish: Dish,
    getDishById: (Int) -> Flow<Dish>,
    onFavoriteClick: () -> Unit,
    onRatingClick: (Int) -> Unit,
) {
    val updateDish by getDishById(dish.id).collectAsState(initial = dish)

    val backgroundTint by animateColorAsState(
        targetValue = if (updateDish!!.isFavorite) MaterialTheme.colorScheme.surfaceContainerHigh else MaterialTheme.colorScheme.surfaceContainerLow,
    )

    val iconTint by animateColorAsState(
        targetValue = if (updateDish!!.isFavorite) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant,
    )
    with(sharedTransitionScope) {
        val roundedCornerAnimation by animatedContentScope.transition
            .animateDp(label = "rounded corner") { enterExit: EnterExitState ->
                when (enterExit) {
                    EnterExitState.PreEnter -> 0.dp
                    EnterExitState.Visible -> 20.dp
                    EnterExitState.PostExit -> 20.dp
                }
            }

        DishCard(
            shape =
                RoundedCornerShape(
                    roundedCornerAnimation,
                ),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .sharedBounds(
                        sharedContentState =
                            rememberSharedContentState(
                                key =
                                    DishSharedElementKey(
                                        dishId = dish.id,
                                        origin = dish.name,
                                        type = DishSharedElementType.Bounds,
                                    ),
                            ),
                        animatedVisibilityScope = animatedContentScope,
                        boundsTransform = dishDetailBoundsTransform,
                        clipInOverlayDuringTransition =
                            OverlayClip(
                                RoundedCornerShape(
                                    roundedCornerAnimation,
                                ),
                            ),
                        enter = fadeIn(),
                        exit = fadeOut(),
                    )
                    .clickable { onDishClick(dish) },
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            elevation = 16.dp,
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxSize(),
            ) {
                Column(
                    modifier =
                        Modifier
                            .align(Alignment.Center),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    ImageLoader(
                        modifier =
                            Modifier
                                .padding(bottom = 8.dp)
                                .size(100.dp)
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
                        imageUrl = dish.imageUrl,
                    )

                    Text(
                        modifier =
                            Modifier
                                .fillMaxWidth()
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
                                )
                                .wrapContentWidth(align = Alignment.CenterHorizontally),
                        text = dish.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                    )
                }

                DishRatingIcon(
                    modifier =
                        Modifier
                            .wrapContentSize(Alignment.TopStart)
                            .padding(top = 4.dp, end = 4.dp, start = 4.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceContainerLow)
                            .size(48.dp),
                    backgroundColorIcon = MaterialTheme.colorScheme.surfaceContainerLow,
                    updatedDish = updateDish,
                    onRatingChanged = onRatingClick,
                )

                Box(
                    modifier =
                        Modifier
                            .align(Alignment.TopEnd)
                            .size(48.dp, 100.dp)
                            .padding(top = 4.dp, end = 4.dp),
                ) {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceAround,
                    ) {
                        FavoriteButton(
                            modifier =
                                Modifier
                                    .wrapContentSize(Alignment.TopEnd)
                                    .clip(CircleShape)
                                    .size(48.dp)
                                    .background(backgroundTint),
                            onFavoriteClick = onFavoriteClick,
                            updatedDish = updateDish,
                            iconTint = iconTint,
                        )

                        DishPrepIcon(
                            Modifier
                                .wrapContentSize(Alignment.TopEnd)
                                .size(48.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DishPrepIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
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
                    .align(Alignment.Center),
            tint = MaterialTheme.colorScheme.secondary,
        )

        Text(
            // dish.time
            text = "30",
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier =
                Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center),
        )
    }
}
