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
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.request.ImageRequest
import com.skydoves.landscapist.coil.CoilImage
import dagger.hilt.android.AndroidEntryPoint
import io.github.jisungbin.sample.R
import io.github.jisungbin.sample.activity.search.mvi.MviUserSearchState
import io.github.jisungbin.sample.domain.model.user.GithubUser
import io.github.jisungbin.sample.theme.MaterialTheme
import io.github.jisungbin.sample.theme.SystemUiController
import io.github.jisungbin.sample.ui.SearchableTopAppBar
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SystemUiController(window).setSystemBarsColor(Color.White)
        setContent {
            MaterialTheme {
                Screen()
            }
        }
    }

    @Composable
    private fun Screen() {
        val vm: SearchViewModel = viewModel()

        val searchingState = remember { mutableStateOf(false) }
        val searchFieldState = remember { mutableStateOf(TextFieldValue()) }

        val scrollingUpState = vm.scrollingUp.observeAsState(true).value
        val scrollState = rememberLazyListState()
        vm.updateScrollPosition(scrollState.firstVisibleItemIndex)

        val searchTopAppBarShadow =
            animateDpAsState(if (!scrollingUpState) 0.dp else AppBarDefaults.BottomAppBarElevation)

        val users = remember { mutableStateListOf<GithubUser>() }

        LaunchedEffect(vm) {
            vm.observe(
                lifecycleOwner = this@SearchActivity,
                state = { state ->
                    handleState(state = state, updateSearchedUserState = { searchedUsers ->
                        users.addAll(searchedUsers)
                    })
                }
            )
        }

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
                        vm.search(query = searchFieldValue.text, page = 1) // todo: pagination
                        println(searchFieldValue)
                    }
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                state = scrollState
            ) {
                items(users) { user ->
                    UserChip(user)
                }
            }
        }
    }

    @Composable
    private fun UserChip(user: GithubUser) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            backgroundColor = Color.White
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = user.loginId,
                    maxLines = 1,
                    color = Color.Black
                )
                CoilImage(
                    imageRequest = ImageRequest.Builder(LocalContext.current)
                        .data(user.loginId)
                        .crossfade(true)
                        .build(),
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop,
                    loading = {
                        Image(
                            painter = painterResource(R.drawable.ic_round_insert_photo_24),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.LightGray),
                            alpha = .5f
                        )
                    }
                )
            }
        }
    }

    private fun handleState(
        state: MviUserSearchState,
        updateSearchedUserState: (List<GithubUser>) -> Unit
    ) {
        if (state.loaded) {
            if (!state.isException()) {
                updateSearchedUserState(state.users)
            } else {
                println(state.exception!!) // todo: handle exception
            }
        }
    }
}
