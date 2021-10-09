/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [SearchActivity.kt] created by Ji Sungbin on 21. 10. 8. 오전 10:42
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.activity.search

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import io.github.jisungbin.githubusers.R
import io.github.jisungbin.githubusers.activity.search.composable.Users
import io.github.jisungbin.githubusers.domain.model.user.GithubUserItem
import io.github.jisungbin.githubusers.theme.MaterialTheme
import io.github.jisungbin.githubusers.ui.SearchableTopAppBar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {

    private val vm: SearchViewModel by viewModels()

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
        val scrollState = rememberLazyListState()
        val focusManager = LocalFocusManager.current
        val coroutineScope = rememberCoroutineScope()

        val searchingState = remember { mutableStateOf(false) }
        val searchFieldState = remember { mutableStateOf(TextFieldValue()) }
        val searchTopAppBarShadow =
            animateDpAsState(if (scrollState.firstVisibleItemIndex != 0) AppBarDefaults.BottomAppBarElevation else 0.dp)

        var usersPaginationFlow by remember { mutableStateOf<Flow<PagingData<GithubUserItem>>?>(null) }

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
                        coroutineScope.launch {
                            focusManager.clearFocus()
                            usersPaginationFlow = vm.searchPagination(searchFieldValue.text)
                        }
                    }
                )
            }
        ) {
            Users(usersPagingDataFlow = usersPaginationFlow, scrollState = scrollState)
        }
    }
}
