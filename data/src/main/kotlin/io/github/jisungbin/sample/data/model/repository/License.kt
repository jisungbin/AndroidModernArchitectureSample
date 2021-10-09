/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [License.kt] created by Ji Sungbin on 21. 10. 9. 오후 3:27
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.model.repository

import com.fasterxml.jackson.annotation.JsonProperty

data class License(
    @field:JsonProperty("name")
    val name: String? = null,

    @field:JsonProperty("spdx_id")
    val spdxId: String? = null,

    @field:JsonProperty("key")
    val key: String? = null,

    @field:JsonProperty("url")
    val url: String? = null,

    @field:JsonProperty("node_id")
    val nodeId: String? = null
)
