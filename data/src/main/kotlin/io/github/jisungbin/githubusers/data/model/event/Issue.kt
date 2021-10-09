/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [Issue.kt] created by Ji Sungbin on 21. 10. 9. 오후 3:41
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.data.model.event

import com.fasterxml.jackson.annotation.JsonProperty

data class Issue(
    @field:JsonProperty("assignees")
    val assignees: List<Any?>? = null,

    @field:JsonProperty("created_at")
    val createdAt: String? = null,

    @field:JsonProperty("title")
    val title: String? = null,

    @field:JsonProperty("body")
    val body: String? = null,

    @field:JsonProperty("labels_url")
    val labelsUrl: String? = null,

    @field:JsonProperty("author_association")
    val authorAssociation: String? = null,

    @field:JsonProperty("number")
    val number: Int? = null,

    @field:JsonProperty("updated_at")
    val updatedAt: String? = null,

    @field:JsonProperty("performed_via_github_app")
    val performedViaGithubApp: Any? = null,

    @field:JsonProperty("comments_url")
    val commentsUrl: String? = null,

    @field:JsonProperty("active_lock_reason")
    val activeLockReason: Any? = null,

    @field:JsonProperty("repository_url")
    val repositoryUrl: String? = null,

    @field:JsonProperty("id")
    val id: Int? = null,

    @field:JsonProperty("state")
    val state: String? = null,

    @field:JsonProperty("locked")
    val locked: Boolean? = null,

    @field:JsonProperty("timeline_url")
    val timelineUrl: String? = null,

    @field:JsonProperty("comments")
    val comments: Int? = null,

    @field:JsonProperty("closed_at")
    val closedAt: Any? = null,

    @field:JsonProperty("url")
    val url: String? = null,

    @field:JsonProperty("labels")
    val labels: List<Any?>? = null,

    @field:JsonProperty("milestone")
    val milestone: Any? = null,

    @field:JsonProperty("events_url")
    val eventsUrl: String? = null,

    @field:JsonProperty("html_url")
    val htmlUrl: String? = null,

    @field:JsonProperty("reactions")
    val reactions: Reactions? = null,

    @field:JsonProperty("assignee")
    val assignee: Any? = null,

    @field:JsonProperty("user")
    val user: User? = null,

    @field:JsonProperty("node_id")
    val nodeId: String? = null
)
