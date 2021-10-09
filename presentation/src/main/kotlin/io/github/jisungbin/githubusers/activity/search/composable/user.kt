/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [user.kt] created by Ji Sungbin on 21. 10. 9. 오후 10:37
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.activity.search.composable

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.landscapist.coil.CoilImage
import io.github.jisungbin.githubusers.activity.profile.ProfileActivity
import io.github.jisungbin.githubusers.domain.model.user.GithubUserItem
import io.github.jisungbin.githubusers.ui.paging.PagingExceptionItem
import io.github.jisungbin.githubusers.ui.paging.PagingLoadingItem
import io.github.jisungbin.githubusers.ui.paging.PagingRefreshItem
import io.github.jisungbin.githubusers.util.constant.IntentConstant
import io.github.jisungbin.sample.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun Users(usersPagingDataFlow: Flow<PagingData<GithubUserItem>>?, scrollState: LazyListState) {
    val swipeRefreshState = rememberSwipeRefreshState(false)
    val coroutineScope = rememberCoroutineScope()

    Crossfade(usersPagingDataFlow != null) { isUserSearched -> // TODO: code clean up
        if (isUserSearched) {
            val usersLazyPagingItems = usersPagingDataFlow!!.collectAsLazyPagingItems()

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    coroutineScope.launch {
                        swipeRefreshState.isRefreshing = true
                        usersLazyPagingItems.refresh()
                        delay(1000)
                        swipeRefreshState.isRefreshing = false
                    }
                },
                indicator = { state, trigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = trigger,
                        scale = true,
                        contentColor = Color.Black
                    )
                }
            ) {
                Crossfade(usersLazyPagingItems.itemCount > 0) { usersNotEmpty ->
                    if (usersNotEmpty) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            state = scrollState,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(items = usersLazyPagingItems) { user ->
                                UserChip(user!!)
                            }
                            usersLazyPagingItems.apply {
                                val exception = loadState.refresh as? LoadState.Error
                                    ?: loadState.prepend as? LoadState.Error
                                    ?: loadState.append as? LoadState.Error

                                when {
                                    loadState.refresh is LoadState.Loading -> { // TODO: Why not working?
                                        item {
                                            PagingRefreshItem(
                                                modifier = Modifier.fillParentMaxSize(),
                                                lottieRes = R.raw.searching_user
                                            )
                                        }
                                    }
                                    loadState.prepend is LoadState.Loading || loadState.append is LoadState.Loading -> { // TODO: Why not prepend working?
                                        item {
                                            PagingLoadingItem()
                                        }
                                    }
                                    exception != null -> {
                                        item {
                                            PagingExceptionItem(
                                                throwable = exception.error,
                                                paginationItems = usersLazyPagingItems
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        SearchingOrEmptyUsers(searching = true)
                    }
                }
            }
        } else {
            SearchingOrEmptyUsers(searching = false)
        }
    }
}

@Composable
private fun UserChip(user: GithubUserItem) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                val profileActivityIntent = Intent(context, ProfileActivity::class.java)
                profileActivityIntent.putExtra(IntentConstant.GithubUserLoginId, user.loginId)
                context.startActivity(profileActivityIntent)
            },
        backgroundColor = Color.White,
        elevation = 2.dp
    ) {
        ConstraintLayout(modifier = Modifier.padding(horizontal = 8.dp)) {
            val (loginId, avatar) = createRefs()

            Text(
                modifier = Modifier.constrainAs(loginId) {
                    start.linkTo(parent.start)
                    end.linkTo(avatar.start, 8.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                },
                text = user.loginId,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black
            )
            CoilImage(
                modifier = Modifier
                    .size(80.dp)
                    .constrainAs(avatar) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                imageModel = user.avatarUrl,
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SearchingOrEmptyUsers(searching: Boolean = false) {
    var lottieRawRes by remember { mutableStateOf(R.raw.empty_user) }
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRawRes))

    if (searching) {
        LaunchedEffect(Unit) {
            lottieRawRes = R.raw.searching_user
            delay(2500)
            lottieRawRes = R.raw.empty_user
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            modifier = Modifier.size(250.dp),
            iterations = LottieConstants.IterateForever,
            composition = lottieComposition,
        )
        AnimatedVisibility(lottieRawRes == R.raw.empty_user) {
            Text(
                modifier = Modifier.padding(top = 30.dp),
                text = stringResource(R.string.activity_search_composable_empty_display_users),
                color = Color.Gray
            )
        }
    }
}
