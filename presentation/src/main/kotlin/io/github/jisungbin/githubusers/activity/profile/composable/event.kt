/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [event.kt] created by Ji Sungbin on 21. 10. 9. 오후 11:52
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.activity.profile.composable

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.skydoves.landscapist.coil.CoilImage
import io.github.jisungbin.githubusers.R
import io.github.jisungbin.githubusers.domain.model.event.GithubUserEventItem
import io.github.jisungbin.githubusers.ui.paging.PagingExceptionItem
import io.github.jisungbin.githubusers.ui.paging.PagingLoadingItem
import io.github.jisungbin.githubusers.util.Web
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Events(eventsPagingDataFlow: Flow<PagingData<GithubUserEventItem>>) {
    val eventsLazyPagingItems = eventsPagingDataFlow.collectAsLazyPagingItems()

    Crossfade(eventsLazyPagingItems.itemCount > 0) { eventsNotEmpty ->
        if (eventsNotEmpty) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items = eventsLazyPagingItems) { event ->
                    EventChip(event!!)
                }
                eventsLazyPagingItems.apply {
                    val exception = loadState.refresh as? LoadState.Error
                        ?: loadState.prepend as? LoadState.Error
                        ?: loadState.append as? LoadState.Error

                    when {
                        loadState.prepend is LoadState.Loading || loadState.append is LoadState.Loading -> { // TODO: Why not prepend working?
                            item {
                                PagingLoadingItem()
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
            EmptyItem(message = stringResource(R.string.activity_profile_composable_empty_event))
        }
    }
}

@Composable
private fun EventChip(event: GithubUserEventItem) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 60.dp, max = 100.dp)
    ) {
        val (avatar, loginId, type, createdAt, repository) = createRefs()

        CoilImage(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .constrainAs(avatar) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
            imageModel = event.avatarUrl,
            contentScale = ContentScale.Crop,
        )
        Text(
            modifier = Modifier.constrainAs(loginId) {
                start.linkTo(avatar.end, 8.dp)
                top.linkTo(avatar.top)
                bottom.linkTo(avatar.bottom)
            },
            text = event.loginId,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.constrainAs(type) {
                start.linkTo(loginId.end, 4.dp)
                top.linkTo(avatar.top)
                bottom.linkTo(avatar.bottom)
            },
            text = event.type.replace("Event", ""),
            color = Color.Black
        )
        Text(
            modifier = Modifier.constrainAs(createdAt) {
                start.linkTo(type.end, 4.dp)
                top.linkTo(avatar.top)
                bottom.linkTo(avatar.bottom)
            },
            text = event.createdAt,
            fontSize = 13.sp,
            color = Color.Gray
        )
        EventRepositoryItem(
            modifier = Modifier.constrainAs(repository) {
                start.linkTo(avatar.end, 8.dp)
                end.linkTo(parent.end)
                top.linkTo(avatar.bottom)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.wrapContent
            },
            repoPatch = event.repoPatch
        )
    }
}

@Composable
private fun EventRepositoryItem(modifier: Modifier, repoPatch: String) {
    val context = LocalContext.current
    val address = "https://github.com/$repoPatch"

    Card(
        modifier = modifier.clickable {
            Web.open(context = context, address = address)
        },
        elevation = 2.dp
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = repoPatch,
            color = Color.Black,
            fontSize = 15.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
