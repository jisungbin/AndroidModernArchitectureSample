/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [SearchableTopAppBar.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:56
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.ui

import android.content.Intent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import io.github.jisungbin.githubusers.R
import io.github.jisungbin.githubusers.util.extension.toast

@Composable
fun SearchableTopAppBar(
    // with OpenSource notice clickable.
    modifier: Modifier = Modifier,
    title: String,
    elevation: Dp = AppBarDefaults.BottomAppBarElevation,
    searchingState: MutableState<Boolean>,
    searchFieldState: MutableState<TextFieldValue>,
    onSearchDoneClickAction: (TextFieldValue) -> Unit,
    primaryColor: Color = LocalContentColor.current.copy(LocalContentAlpha.current),
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
) {
    val context = LocalContext.current

    TopAppBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
        elevation = elevation,
        contentPadding = PaddingValues(start = 16.dp, end = 8.dp)
    ) {
        Crossfade(searchingState.value) { isSearching ->
            if (isSearching) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(FocusRequester()),
                    value = searchFieldState.value,
                    onValueChange = { value ->
                        searchFieldState.value = value
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.ui_search_topappbar_placeholder_input),
                            color = Color.LightGray
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (searchFieldState.value.text.isNotEmpty()) {
                                    searchFieldState.value = TextFieldValue("")
                                } else {
                                    searchingState.value = false
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_round_close_24),
                                contentDescription = null,
                                tint = primaryColor
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions { onSearchDoneClickAction(searchFieldState.value) },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = primaryColor,
                        cursorColor = primaryColor,
                        trailingIconColor = primaryColor,
                        backgroundColor = backgroundColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.clickable {
                            context.startActivity(
                                Intent(context, OssLicensesMenuActivity::class.java)
                            )
                            toast(context, "⭐")
                        },
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = primaryColor
                    )
                    IconButton(onClick = { searchingState.value = true }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_round_search_24),
                            contentDescription = null,
                            tint = primaryColor
                        )
                    }
                }
            }
        }
    }
}
