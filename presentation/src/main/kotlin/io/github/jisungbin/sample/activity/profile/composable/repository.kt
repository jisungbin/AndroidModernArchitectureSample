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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
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
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
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
            .fillMaxWidth()
            .height(100.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = repository.name, color = Color.Black, fontSize = 18.sp)
            Text(text = repository.description, color = Color.Gray, fontSize = 15.sp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                RepositoryInformationChip(
                    iconRes = R.drawable.ic_round_circle_24,
                    text = repository.language
                )
                RepositoryInformationChip(
                    iconRes = R.drawable.ic_round_star_24,
                    text = repository.stargazersCount.toString()
                )
                RepositoryInformationChip(
                    modifier = Modifier.clickable {
                        Web.open(context = context, address = address)
                    },
                    iconRes = R.drawable.ic_baseline_insert_link_24,
                    text = address.replace("https://", "")
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
        Icon(painter = painterResource(iconRes), contentDescription = null, tint = Color.Gray)
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = text,
            color = Color.Gray,
            fontSize = 13.sp
        )
    }
}