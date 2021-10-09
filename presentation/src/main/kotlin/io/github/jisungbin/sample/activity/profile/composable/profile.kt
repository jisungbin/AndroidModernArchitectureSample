/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [profile.kt] created by Ji Sungbin on 21. 10. 9. 오후 10:35
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.activity.profile.composable

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.landscapist.coil.CoilImage
import io.github.jisungbin.sample.R
import io.github.jisungbin.sample.activity.profile.ProfileViewModel
import io.github.jisungbin.sample.domain.model.information.GithubUserInformation
import io.github.jisungbin.sample.domain.model.repository.GithubUserRepositories
import io.github.jisungbin.sample.domain.model.repository.GithubUserRepositoryItem
import io.github.jisungbin.sample.domain.model.user.GithubUserItem
import io.github.jisungbin.sample.ui.PagingExceptionItem
import io.github.jisungbin.sample.util.Web
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun Profile(user: GithubUserInformation) {
    val vm: ProfileViewModel = viewModel()
    val swipeRefreshState = rememberSwipeRefreshState(false)
    val coroutineScope = rememberCoroutineScope()

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            coroutineScope.launch {
                swipeRefreshState.isRefreshing = true
                // usersLazyPagingItems.refresh()
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

    }
}

@Composable
private fun Header(user: GithubUserInformation) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(15.dp),
    ) {
        val (profileContainer, avatar) = createRefs()

        Column(
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(profileContainer) {
                    start.linkTo(parent.start)
                    end.linkTo(avatar.start, 8.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = user.loginId,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black
            )
            Text(
                text = user.bio,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = Color.Gray
            )
        }
        CoilImage(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(5.dp))
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

@Composable
fun Repositories(_repositories: GithubUserRepositories) {
    val repositories = _repositories.items

    if (repositories.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = repositories) { repository ->
                RepositoryItem(repository = repository)
            }
        }
    } else {
    }
}

@Composable
private fun RepositoryItem(repository: GithubUserRepositoryItem) {
    val context = LocalContext.current
    val address = "https://github.com/${repository.ownerLoginId}/${repository.name}"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp)
    ) {
        Text(text = repository.name, color = Color.Black, fontSize = 18.sp)
        Text(text = repository.description, color = Color.Gray, fontSize = 15.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            RepositoryInformationChip(
                iconRes = R.drawable.ic_round_circle_24,
                text = repository.language
            )
            RepositoryInformationChip(
                iconRes = R.drawable.ic_round_star_24,
                text = repository.stargazersCount.toString()
            )
            RepositoryInformationChip(
                modifier = Modifier.clickable {
                    Web.open(context, address)
                },
                iconRes = R.drawable.ic_baseline_insert_link_24,
                text = address.replace("https://", "")
            )
        }
    }
}

@Composable
private fun RepositoryInformationChip(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    text: String
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(iconRes), contentDescription = null, tint = Color.Gray)
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = text,
            color = Color.Gray,
            fontSize = 13.sp
        )
    }
}

@Composable
fun Events(eventsPagingDataFlow: Flow<PagingData<GithubUserItem>>?) {
    if (eventsPagingDataFlow != null) {
        val eventsLazyPagingItems = eventsPagingDataFlow.collectAsLazyPagingItems()

        if (eventsLazyPagingItems.itemCount > 0) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items = eventsLazyPagingItems) { event ->
                    // (user!!)
                }
                eventsLazyPagingItems.apply {
                    val exception = loadState.refresh as? LoadState.Error
                        ?: loadState.prepend as? LoadState.Error
                        ?: loadState.append as? LoadState.Error

                    when {
                        loadState.prepend is LoadState.Loading || loadState.append is LoadState.Loading -> { // TODO: Why not prepend working?
                            item {
                                // LoadingItem()
                            }
                        }
                        exception != null -> {
                            item {
                                PagingExceptionItem(
                                    throwable = exception.error,
                                    paginationItems = eventsLazyPagingItems
                                )
                            }
                        }
                    }
                }
            }
        } else {
            LoadingOrEmptyItem(message = stringResource(R.string.activity_profile_composable_empty_event))
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun LoadingOrEmptyItem(message: String) {
    var empty by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(2500)
        empty = true
    }

    AnimatedVisibility(empty) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentWidth(),
            text = message,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}
