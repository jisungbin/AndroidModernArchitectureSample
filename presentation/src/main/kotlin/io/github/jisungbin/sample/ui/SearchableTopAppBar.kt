/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [SearchableTopAppBar.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:56
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.ui

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.jisungbin.sample.R

@Composable
fun SearchableTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    elevation: Dp = AppBarDefaults.BottomAppBarElevation,
    searchingState: MutableState<Boolean>,
    searchFieldState: MutableState<TextFieldValue>,
    onSearchDoneClickAction: (TextFieldValue) -> Unit,
    primaryColor: Color = LocalContentColor.current.copy(LocalContentAlpha.current),
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    iconTint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
        elevation = elevation,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        if (searchingState.value) {
            TextField(
                value = searchFieldState.value,
                onValueChange = { value ->
                    searchFieldState.value = value
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                trailingIcon = {
                    if (searchFieldState.value.text.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                searchFieldState.value = TextFieldValue("")
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_round_close_24),
                                contentDescription = null,
                                tint = iconTint
                            )
                        }
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
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = primaryColor
                )
                IconButton(
                    onClick = {
                        searchingState.value = true
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_round_search_24),
                        contentDescription = null,
                        tint = iconTint
                    )
                }
            }
        }
    }
}
