/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [Author.kt] created by Ji Sungbin on 21. 10. 9. 오후 3:40
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.model.event

import com.fasterxml.jackson.annotation.JsonProperty

data class Author(
    @field:JsonProperty("name")
    val name: String? = null,

    @field:JsonProperty("email")
    val email: String? = null
)
