package com.devfalah.ui.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.Screen
import com.devfalah.ui.composable.ManualPager
import com.devfalah.ui.composable.PostItem
import com.devfalah.ui.composable.SetStatusBarColor
import com.devfalah.ui.screen.profile.composable.PostCreatingSection
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.home.HomeUIState
import com.devfalah.viewmodels.home.HomeViewModel
import com.devfalah.viewmodels.userProfile.PostUIState
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    SetStatusBarColor(LightBackgroundColor, darkIcons = true)

    val state by viewModel.uiState.collectAsState()
    HomeContent(
        state = state,
        swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading),
        onClickLike = viewModel::onClickLike,
        // should navigate to post screen details.
        onClickComment = { navController.navigate(Screen.CreatePost.screen_route) },
        onClickSave = viewModel::onClickSave,
        onCreatePost = { navController.navigate(Screen.CreatePost.screen_route) },
        onRefresh = viewModel::swipeToRefresh,
        onClickProfile = { navController.navigateToProfile(it) }
    )
}

@Composable
fun HomeContent(
    state: HomeUIState,
    swipeRefreshState: SwipeRefreshState,
    onCreatePost: () -> Unit,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onRefresh: (Int) -> Unit,
    onClickProfile: (Int) -> Unit
) {
    val scrollState = rememberLazyListState()

    ManualPager(
        swipeRefreshState = swipeRefreshState,
        onRefresh = onRefresh,
        items = state.posts,
        scrollState = scrollState,
        isRefreshing = state.isPagerLoading,
        error = state.pagerError,
        contentPadding = PaddingValues(vertical = 16.dp)

    ) {

        item {
            PostCreatingSection(
                modifier = Modifier.padding(horizontal = 16.dp),
                onCreatePost = onCreatePost,
                isMyProfile = true
            )
        }

        items(state.posts) {
            PostItem(
                state = it,
                isContentExpandable = true,
                isMyPost = it.publisherId == state.id,
                onClickLike = { onClickLike(it) },
                onClickComment = { onClickComment(it) },
                onClickSave = { onClickSave(it) },
                onClickProfile = onClickProfile,
                onClickPostSetting = { },
            )
        }
    }
}
