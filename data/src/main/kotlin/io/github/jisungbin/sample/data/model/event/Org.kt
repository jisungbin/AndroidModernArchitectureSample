/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [Org.kt] created by Ji Sungbin on 21. 10. 9. 오후 3:41
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.model.event

import com.fasterxml.jackson.annotation.JsonProperty

data class Org(
    @field:JsonProperty("avatar_url")
    val avatarUrl: String? = null,

    @field:JsonProperty("id")
    val id: Int? = null,

    @field:JsonProperty("login")
    val login: String? = null,

    @field:JsonProperty("gravatar_id")
    val gravatarId: String? = null,

    @field:JsonProperty("url")
    val url: String? = null
)
