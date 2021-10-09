/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUser.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:21
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class GithubUserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val loginId: String,
    val avatarUrl: String,
    val searchKeyword: String
)
