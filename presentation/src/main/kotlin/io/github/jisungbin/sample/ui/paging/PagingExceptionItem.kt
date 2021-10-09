/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [PagingExceptionItem.kt] created by Ji Sungbin on 21. 10. 9. 오후 11:30
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.ui.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import io.github.jisungbin.sample.R

@Composable
fun <T : Any> PagingExceptionItem(
    throwable: Throwable,
    paginationItems: LazyPagingItems<T>
) {
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
                R.string.ui_paging_exception_item_message,
                throwable.message.toString()
            ),
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier.padding(top = 5.dp),
            onClick = { paginationItems.retry() }
        ) {
            Text(
                text = stringResource(R.string.ui_paging_exception_item_button_retry),
                color = Color.Gray
            )
        }
    }
}
