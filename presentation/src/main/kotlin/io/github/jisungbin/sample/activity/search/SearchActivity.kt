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
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import coil.request.ImageRequest
import com.skydoves.landscapist.coil.CoilImage
import dagger.hilt.android.AndroidEntryPoint
import io.github.jisungbin.sample.R
import io.github.jisungbin.sample.domain.model.user.GithubUser
import io.github.jisungbin.sample.theme.MaterialTheme
import io.github.jisungbin.sample.theme.SystemUiController
import io.github.jisungbin.sample.ui.SearchableTopAppBar

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {

    private val vm: SearchViewModel by viewModels()

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
        val searchingState = remember { mutableStateOf(false) }
        val searchFieldState = remember { mutableStateOf(TextFieldValue()) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                SearchableTopAppBar(
                    title = stringResource(R.string.app_name),
                    searchingState = searchingState,
                    searchFieldState = searchFieldState,
                    onSearchDoneClickAction = { println(it) }
                )
            }
        ) {

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
}
