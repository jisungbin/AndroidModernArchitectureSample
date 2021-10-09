/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [CommitsItem.kt] created by Ji Sungbin on 21. 10. 9. 오후 3:40
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.model.event

import com.fasterxml.jackson.annotation.JsonProperty

data class CommitsItem(
    @field:JsonProperty("author")
    val author: Author? = null,

    @field:JsonProperty("distinct")
    val distinct: Boolean? = null,

    @field:JsonProperty("message")
    val message: String? = null,

    @field:JsonProperty("sha")
    val sha: String? = null,

    @field:JsonProperty("url")
    val url: String? = null
)
