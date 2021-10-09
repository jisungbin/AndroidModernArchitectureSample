/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserEventItem.kt] created by Ji Sungbin on 21. 10. 9. 오후 3:41
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.model.event

import com.fasterxml.jackson.annotation.JsonProperty

data class GithubUserEventItem(
    @field:JsonProperty("actor")
    val actor: Actor? = null,

    @field:JsonProperty("public")
    val jsonMemberPublic: Boolean? = null,

    @field:JsonProperty("payload")
    val payload: Payload? = null,

    @field:JsonProperty("repo")
    val repo: Repo? = null,

    @field:JsonProperty("created_at")
    val createdAt: String? = null,

    @field:JsonProperty("id")
    val id: String? = null,

    @field:JsonProperty("type")
    val type: String? = null,

    @field:JsonProperty("org")
    val org: Org? = null
)
