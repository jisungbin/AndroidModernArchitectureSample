/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [Reactions.kt] created by Ji Sungbin on 21. 10. 9. 오후 3:42
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.model.event

import com.fasterxml.jackson.annotation.JsonProperty

data class Reactions(
    @field:JsonProperty("confused")
    val confused: Int? = null,

    @field:JsonProperty("-1")
    val minusOne: Int? = null,

    @field:JsonProperty("total_count")
    val totalCount: Int? = null,

    @field:JsonProperty("+1")
    val plusOne: Int? = null,

    @field:JsonProperty("rocket")
    val rocket: Int? = null,

    @field:JsonProperty("hooray")
    val hooray: Int? = null,

    @field:JsonProperty("eyes")
    val eyes: Int? = null,

    @field:JsonProperty("url")
    val url: String? = null,

    @field:JsonProperty("laugh")
    val laugh: Int? = null,

    @field:JsonProperty("heart")
    val heart: Int? = null
)
