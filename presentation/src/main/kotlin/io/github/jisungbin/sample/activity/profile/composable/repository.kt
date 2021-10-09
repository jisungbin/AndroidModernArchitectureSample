/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [repository.kt] created by Ji Sungbin on 21. 10. 9. 오후 11:52
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.activity.profile.composable

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.jisungbin.sample.R
import io.github.jisungbin.sample.domain.model.repository.GithubUserRepositories
import io.github.jisungbin.sample.domain.model.repository.GithubUserRepositoryItem
import io.github.jisungbin.sample.util.Web

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Repositories(repositories: GithubUserRepositories?) {
    AnimatedVisibility(repositories != null) {
        val repositoriesItem = repositories!!.items

        Crossfade(repositoriesItem.isNotEmpty()) { repositoriesNotEmpty ->
            if (repositoriesNotEmpty) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(items = repositoriesItem) { repository ->
                        RepositoryItem(repository = repository)
                    }
                }
            } else {
                LoadingOrEmptyItem(stringResource(R.string.activity_profile_composable_empty_repository))
            }
        }
    }
}

@Composable
private fun RepositoryItem(repository: GithubUserRepositoryItem) {
    val context = LocalContext.current
    val address = "https://github.com/${repository.ownerLoginId}/${repository.name}"

    Card(
        modifier = Modifier
            .widthIn(max = 300.dp)
            .height(110.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    Web.open(context = context, address = address)
                }
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = repository.name,
                color = Color.Black,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = repository.description,
                color = Color.Gray,
                fontSize = 13.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RepositoryInformationChip(
                    iconRes = R.drawable.ic_round_circle_24,
                    text = repository.language
                )
                RepositoryInformationChip(
                    iconRes = R.drawable.ic_round_star_24,
                    text = repository.stargazersCount.toString()
                )
            }
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
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color.Gray
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = text,
            color = Color.Gray,
            fontSize = 13.sp
        )
    }
}
