/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [SearchActivity.kt] created by Ji Sungbin on 21. 10. 8. 오전 10:42
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.activity.search

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.skydoves.landscapist.coil.CoilImage
import dagger.hilt.android.AndroidEntryPoint
import io.github.jisungbin.sample.R
import io.github.jisungbin.sample.domain.model.user.GithubUser
import io.github.jisungbin.sample.theme.MaterialTheme
import io.github.jisungbin.sample.ui.SearchableTopAppBar
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Screen()
            }
        }
    }

    @Composable
    private fun Screen() {
        val vm: SearchViewModel = viewModel()
        val scrollState = rememberLazyListState()
        val focusManager = LocalFocusManager.current

        val searchingState = remember { mutableStateOf(false) }
        val searchFieldState = remember { mutableStateOf(TextFieldValue()) }
        val searchTopAppBarShadow =
            animateDpAsState(if (scrollState.firstVisibleItemIndex != 0) AppBarDefaults.BottomAppBarElevation else 0.dp)

        var userPaginationFlow by remember { mutableStateOf<Flow<PagingData<GithubUser>>?>(null) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                SearchableTopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    title = stringResource(R.string.app_name),
                    elevation = searchTopAppBarShadow.value,
                    searchingState = searchingState,
                    searchFieldState = searchFieldState,
                    primaryColor = Color.Black,
                    backgroundColor = Color.White,
                    onSearchDoneClickAction = { searchFieldValue ->
                        focusManager.clearFocus()
                        userPaginationFlow = vm.searchPagination(searchFieldValue.text)
                    }
                )
            }
        ) {
            Crossfade(userPaginationFlow != null) { isUserSearched ->
                if (isUserSearched) {
                    val users = userPaginationFlow!!.collectAsLazyPagingItems()
                    if (users.itemCount > 0) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            state = scrollState,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(items = users) { user ->
                                UserChip(user!!)
                            }

                            users.apply {
                                when {
                                    loadState.refresh is LoadState.Loading -> {
                                        item {
                                            SearchingItem(Modifier.fillParentMaxSize())
                                        }
                                    }
                                    loadState.prepend is LoadState.Loading -> {
                                        item { LoadingItem() }
                                    }
                                    loadState.append is LoadState.Loading -> {
                                        item { LoadingItem() }
                                    }
                                    loadState.refresh is LoadState.Error -> {
                                        val exception = users.loadState.refresh as LoadState.Error
                                        item {
                                            PagingExceptionItem(exception.error)
                                        }
                                    }
                                    loadState.prepend is LoadState.Error -> {
                                        val exception = users.loadState.prepend as LoadState.Error
                                        item {
                                            PagingExceptionItem(exception.error)
                                        }
                                    }
                                    loadState.append is LoadState.Error -> {
                                        val exception = users.loadState.append as LoadState.Error
                                        item {
                                            PagingExceptionItem(exception.error)
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        EmptyUsers()
                    }
                } else {
                    EmptyUsers()
                }
            }
        }
    }

    @Composable
    private fun UserChip(user: GithubUser) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            backgroundColor = Color.White,
            elevation = 2.dp
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = user.loginId,
                    maxLines = 1,
                    color = Color.Black
                )
                CoilImage(
                    imageModel = user.avatarUrl,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }

    @Composable
    private fun EmptyUsers() {
        val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty))

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
            Text(
                modifier = Modifier.padding(top = 30.dp),
                text = stringResource(R.string.activity_search_text_empty_display_users),
                color = Color.Gray
            )
        }
    }

    @Composable
    private fun SearchingItem(modifier: Modifier) {
        val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.searching))

        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                modifier = Modifier.size(250.dp),
                iterations = LottieConstants.IterateForever,
                composition = lottieComposition,
            )
        }
    }

    @Composable
    private fun PagingExceptionItem(throwable: Throwable) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(
                    R.string.activity_search_text_exception,
                    throwable.message.toString()
                ),
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    private fun LoadingItem() {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}
