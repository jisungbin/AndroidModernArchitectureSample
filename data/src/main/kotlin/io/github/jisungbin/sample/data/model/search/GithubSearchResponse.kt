/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserResponse.kt] created by Ji Sungbin on 21. 10. 8. 오후 3:58
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.model.search

import com.fasterxml.jackson.annotation.JsonProperty

data class GithubSearchResponse(
    @field:JsonProperty("total_count")
    val totalCount: Int,

    @field:JsonProperty("incomplete_results")
    val incompleteResults: Boolean,

    @field:JsonProperty("items")
    val items: List<UserItem>
)
