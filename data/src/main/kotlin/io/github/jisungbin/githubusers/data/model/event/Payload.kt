/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [Payload.kt] created by Ji Sungbin on 21. 10. 9. 오후 3:42
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.data.model.event

import com.fasterxml.jackson.annotation.JsonProperty

data class Payload(
    @field:JsonProperty("head")
    val head: String? = null,

    @field:JsonProperty("ref")
    val ref: String? = null,

    @field:JsonProperty("size")
    val size: Int? = null,

    @field:JsonProperty("before")
    val before: String? = null,

    @field:JsonProperty("push_id")
    val pushId: Long? = null,

    @field:JsonProperty("distinct_size")
    val distinctSize: Int? = null,

    @field:JsonProperty("commits")
    val commits: List<CommitsItem?>? = null,

    @field:JsonProperty("action")
    val action: String? = null,

    @field:JsonProperty("issue")
    val issue: Issue? = null
)
