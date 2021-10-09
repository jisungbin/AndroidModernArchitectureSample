/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [ProfileActivity.kt] created by Ji Sungbin on 21. 10. 8. 오전 11:24
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.activity.profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.coil.CoilImage
import dagger.hilt.android.AndroidEntryPoint
import io.github.jisungbin.sample.domain.model.information.GithubUserInformation
import io.github.jisungbin.sample.theme.MaterialTheme

@AndroidEntryPoint
class ProfileActivity : ComponentActivity() {

    private val vm: ProfileViewModel by viewModels()

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
    }

    @Composable
    private fun Profile(_user: State<GithubUserInformation>) {
        val user = _user.value

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(15.dp),
        ) {
            val (profileContainer, avatar) = createRefs()

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(profileContainer) {
                        start.linkTo(parent.start)
                        end.linkTo(avatar.start, 8.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = user.loginId,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )
                Text(
                    text = user.bio,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray
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
                    },
                imageModel = user.avatarUrl,
                contentScale = ContentScale.Crop,
            )
        }
    }

    @Composable
    private fun Repositories() {
    }

    @Composable
    private fun Events() {
    }
}
