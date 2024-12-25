@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.test.ui.dish

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.R
import com.example.test.model.Dish
import com.example.test.ui.components.DishCard
import com.example.test.ui.components.DishRatingIcon
import com.example.test.ui.components.FavoriteButton
import com.example.test.ui.components.ImageLoader
import com.example.test.ui.components.TopBarContent
import com.example.test.ui.theme.TestTheme
import com.example.test.utils.DishSharedElementKey
import com.example.test.utils.DishSharedElementType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishListScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope,
    paddingValues: PaddingValues,
    type: String,
    viewModel: DishViewModel,
    onDishClick: (Dish) -> Unit,
    onPressBack: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val searchText by viewModel.searchText.collectAsState()
    val dishListByType by viewModel.getFilteredDishesByType(type).collectAsState()

    var isSearch by remember { mutableStateOf(true) }

    Scaffold(
        modifier =
            Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    TopBarContent(
                        isSearch = isSearch,
                        searchText = searchText,
                        onSearchTextChange = viewModel::onSearchTextChange,
                        screenTitle = "Список блюд для типа: $type",
                    )
                },
                actions = {
                    IconButton(onClick = { isSearch = !isSearch }) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Поиск",
                        )
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        titleContentColor = MaterialTheme.colorScheme.onSurface,
                        navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
                navigationIcon = {
                    if (!isSearch) {
                        IconButton(
                            onClick = {
                                isSearch = !isSearch
                                viewModel.clearSearchText()
                            },
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
                    } else {
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
                    }
                },
            )
        },
    ) {
        Column(
            modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxSize()
                    .padding(
                        top = it.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                    ),
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp),
//                horizontalAlignment = Alignment.,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier =
                    Modifier
                        .fillMaxSize()
                        .pointerInput(isSearch) {
                            detectTapGestures(onTap = {
                                if (!isSearch) {
                                    isSearch = true
                                    viewModel.clearSearchText()
                                }
                            })
                        },
            ) {
                items(dishListByType) { dish ->
                    ListTypeCard(
                        sharedTransitionScope = sharedTransitionScope,
                        animatedContentScope = animatedContentScope,
                        onDishClick = onDishClick,
                        dish = dish,
                        viewModel,
                    )
                }
            }
        }
    }
}

@Composable
fun ListTypeCard(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope,
    onDishClick: (Dish) -> Unit,
    dish: Dish,
    viewModel: DishViewModel,
) {
    val updatedDish = viewModel.dishesFlow.collectAsState().value.find { it.id == dish.id }
    val isFavorite by rememberUpdatedState(updatedDish?.isFavorite ?: dish.isFavorite)

    val backgroundTint by animateColorAsState(
        targetValue = if (isFavorite) MaterialTheme.colorScheme.surfaceContainerHigh else MaterialTheme.colorScheme.surfaceContainerLow,
    )

    val iconTint by animateColorAsState(
        targetValue = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant,
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
                                        origin = dish.name.toString(),
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
            shape = MaterialTheme.shapes.medium,
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
                                                origin = dish.name.toString(),
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
                                                origin = dish.name.toString(),
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
                    updatedDish = updatedDish,
                ) { rating ->
                    viewModel.toggleRating(dish.id, rating)
                }

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
                            onFavoriteClick = { viewModel.toggleFavorite(dish.id) },
                            updatedDish = updatedDish,
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

@Preview(
    name = "Light theme",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Dark theme",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun DishCardPreview() {
    val viewModel = DishViewModel()

    SharedTransitionLayout {
        val paddingValues = PaddingValues(16.dp)
        AnimatedVisibility(visible = true) {
            TestTheme {
                DishListScreen(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this,
                    paddingValues = paddingValues,
                    type = "Супы",
                    viewModel = viewModel,
                    onDishClick = {},
                    onPressBack = {},
                )
            }
        }
    }
}

// @Composable
// fun PreviewWrapper(content: @Composable () -> Unit) {
//    TestTheme {
//        SharedTransitionLayout {
//            AnimatedVisibility(visible = true) {
//                CompositionLocalProvider(
//                    LocalSharedTransitionScope provides this@SharedTransitionLayout,
//                    LocalNavAnimatedVisibilityScope provides this,
//                ) {
//                    content()
//                }
//            }
//        }
//    }
// }
