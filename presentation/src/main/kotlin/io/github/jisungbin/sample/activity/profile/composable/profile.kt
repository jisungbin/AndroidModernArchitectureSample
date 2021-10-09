/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [profile.kt] created by Ji Sungbin on 21. 10. 9. 오후 10:35
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.activity.profile.composable

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.landscapist.coil.CoilImage
import io.github.jisungbin.sample.R
import io.github.jisungbin.sample.activity.profile.ImageViewActivity
import io.github.jisungbin.sample.activity.profile.ProfileViewModel
import io.github.jisungbin.sample.activity.profile.mvi.MviProfileState
import io.github.jisungbin.sample.domain.model.event.GithubUserEventItem
import io.github.jisungbin.sample.domain.model.information.GithubUserInformation
import io.github.jisungbin.sample.domain.model.repository.GithubUserRepositories
import io.github.jisungbin.sample.ui.paging.PagingRefreshItem
import io.github.jisungbin.sample.util.constant.IntentConstant
import io.github.jisungbin.sample.util.extension.toast
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe

@Composable
fun Profile(loginId: String) {
    val context = LocalContext.current
    val vm: ProfileViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val lifeCycleOwner = LocalLifecycleOwner.current
    val swipeRefreshState = rememberSwipeRefreshState(false)

    var userInformation by remember { mutableStateOf<GithubUserInformation?>(null) }
    var userRepositories by remember { mutableStateOf<GithubUserRepositories?>(null) }
    var userEventsPagingData by remember {
        mutableStateOf<Flow<PagingData<GithubUserEventItem>>?>(null)
    }

    suspend fun refresh() {
        vm.getUserInformation(loginId)
        vm.getUserRepositories(loginId)
        userEventsPagingData = vm.getUserEventsPagination(loginId)
    }

    LaunchedEffect(vm) {
        vm.observe(
            lifecycleOwner = lifeCycleOwner,
            state = { state ->
                handleState(
                    context = context,
                    state = state,
                    updateUserInformation = { _userInformation ->
                        userInformation = _userInformation
                    },
                    updateUserRepositories = { _userRepositories ->
                        userRepositories = _userRepositories
                    }
                )
            },
            sideEffect = null
        )
        refresh()
    }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            coroutineScope.launch {
                swipeRefreshState.isRefreshing = true
                refresh()
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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Header(user = userInformation)
            Repositories(repositories = userRepositories)
            Events(eventsPagingDataFlow = userEventsPagingData)
        }
    }
}

@Composable
private fun Header(user: GithubUserInformation?) {
    val context = LocalContext.current

    Crossfade(user != null) { isUserLoaded ->
        if (isUserLoaded) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
            ) {
                val (profileContainer, avatar) = createRefs()

                Column(
                    modifier = Modifier
                        .constrainAs(profileContainer) {
                            start.linkTo(parent.start, 8.dp)
                            end.linkTo(avatar.start, 8.dp)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = user!!.loginId,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = user.bio,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        fontSize = 13.sp
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
                        }
                        .clickable {
                            val imageViewActivityIntent =
                                Intent(context, ImageViewActivity::class.java)
                            imageViewActivityIntent.putExtra(
                                IntentConstant.ImageUrl,
                                user!!.avatarUrl
                            )
                            context.startActivity(imageViewActivityIntent)
                        },
                    imageModel = user!!.avatarUrl,
                    contentScale = ContentScale.Crop,
                )
            }
        } else {
            PagingRefreshItem(modifier = Modifier.fillMaxSize(), lottieRes = R.raw.loading_profile)
        }
    }
}

private fun handleState(
    context: Context,
    state: MviProfileState,
    updateUserInformation: (GithubUserInformation) -> Unit,
    updateUserRepositories: (GithubUserRepositories) -> Unit,
) {
    if (state.loaded) {
        if (!state.isException()) {
            when {
                state.userInformation != null -> updateUserInformation(state.userInformation)
                state.userRepositories != null -> updateUserRepositories(state.userRepositories)
            }
        } else {
            toast(
                context = context,
                message = context.getString(
                    R.string.ui_paging_exception_item_message,
                    state.exception?.message ?: "Can't load exception message"
                ),
                length = Toast.LENGTH_LONG
            )
        }
    }
}
