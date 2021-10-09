/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserEventEntity.kt] created by Ji Sungbin on 21. 10. 9. 오후 4:23
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class GithubUserEventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val avatarUrl: String,
    val loginId: String,
    val type: String,
    val createdAt: String,
    val repoPatch: String
)
