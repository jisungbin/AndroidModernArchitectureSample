/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [ItemsItem.kt] created by Ji Sungbin on 21. 10. 8. 오후 3:58
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.model.search

import com.fasterxml.jackson.annotation.JsonProperty

data class UserItem(
    @field:JsonProperty("gists_url")
    val gistsUrl: String,

    @field:JsonProperty("repos_url")
    val reposUrl: String,

    @field:JsonProperty("following_url")
    val followingUrl: String,

    @field:JsonProperty("starred_url")
    val starredUrl: String,

    @field:JsonProperty("login")
    val loginId: String,

    @field:JsonProperty("followers_url")
    val followersUrl: String,

    @field:JsonProperty("type")
    val type: String,

    @field:JsonProperty("url")
    val url: String,

    @field:JsonProperty("subscriptions_url")
    val subscriptionsUrl: String,

    @field:JsonProperty("score")
    val score: Double,

    @field:JsonProperty("received_events_url")
    val receivedEventsUrl: String,

    @field:JsonProperty("avatar_url")
    val avatarUrl: String,

    @field:JsonProperty("events_url")
    val eventsUrl: String,

    @field:JsonProperty("html_url")
    val htmlUrl: String,

    @field:JsonProperty("site_admin")
    val siteAdmin: Boolean,

    @field:JsonProperty("id")
    val id: Int,

    @field:JsonProperty("gravatar_id")
    val gravatarId: String,

    @field:JsonProperty("node_id")
    val nodeId: String,

    @field:JsonProperty("organizations_url")
    val organizationsUrl: String
)
