/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserRepositoryItem.kt] created by Ji Sungbin on 21. 10. 9. 오후 3:29
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.model.repository

import com.fasterxml.jackson.annotation.JsonProperty

data class GithubUserRepositoryItem(
    @field:JsonProperty("allow_forking")
    val allowForking: Boolean? = null,

    @field:JsonProperty("stargazers_count")
    val stargazersCount: Int? = null,

    @field:JsonProperty("is_template")
    val isTemplate: Boolean? = null,

    @field:JsonProperty("pushed_at")
    val pushedAt: String? = null,

    @field:JsonProperty("subscription_url")
    val subscriptionUrl: String? = null,

    @field:JsonProperty("language")
    val language: String? = null,

    @field:JsonProperty("branches_url")
    val branchesUrl: String? = null,

    @field:JsonProperty("issue_comment_url")
    val issueCommentUrl: String? = null,

    @field:JsonProperty("labels_url")
    val labelsUrl: String? = null,

    @field:JsonProperty("subscribers_url")
    val subscribersUrl: String? = null,

    @field:JsonProperty("releases_url")
    val releasesUrl: String? = null,

    @field:JsonProperty("svn_url")
    val svnUrl: String? = null,

    @field:JsonProperty("id")
    val id: Int? = null,

    @field:JsonProperty("forks")
    val forks: Int? = null,

    @field:JsonProperty("archive_url")
    val archiveUrl: String? = null,

    @field:JsonProperty("git_refs_url")
    val gitRefsUrl: String? = null,

    @field:JsonProperty("forks_url")
    val forksUrl: String? = null,

    @field:JsonProperty("visibility")
    val visibility: String? = null,

    @field:JsonProperty("statuses_url")
    val statusesUrl: String? = null,

    @field:JsonProperty("ssh_url")
    val sshUrl: String? = null,

    @field:JsonProperty("license")
    val license: License? = null,

    @field:JsonProperty("full_name")
    val fullName: String? = null,

    @field:JsonProperty("size")
    val size: Int? = null,

    @field:JsonProperty("languages_url")
    val languagesUrl: String? = null,

    @field:JsonProperty("html_url")
    val htmlUrl: String? = null,

    @field:JsonProperty("collaborators_url")
    val collaboratorsUrl: String? = null,

    @field:JsonProperty("clone_url")
    val cloneUrl: String? = null,

    @field:JsonProperty("name")
    val name: String? = null,

    @field:JsonProperty("pulls_url")
    val pullsUrl: String? = null,

    @field:JsonProperty("default_branch")
    val defaultBranch: String? = null,

    @field:JsonProperty("hooks_url")
    val hooksUrl: String? = null,

    @field:JsonProperty("trees_url")
    val treesUrl: String? = null,

    @field:JsonProperty("tags_url")
    val tagsUrl: String? = null,

    @field:JsonProperty("private")
    val jsonMemberPrivate: Boolean? = null,

    @field:JsonProperty("contributors_url")
    val contributorsUrl: String? = null,

    @field:JsonProperty("has_downloads")
    val hasDownloads: Boolean? = null,

    @field:JsonProperty("notifications_url")
    val notificationsUrl: String? = null,

    @field:JsonProperty("open_issues_count")
    val openIssuesCount: Int? = null,

    @field:JsonProperty("description")
    val description: String? = null,

    @field:JsonProperty("created_at")
    val createdAt: String? = null,

    @field:JsonProperty("watchers")
    val watchers: Int? = null,

    @field:JsonProperty("keys_url")
    val keysUrl: String? = null,

    @field:JsonProperty("deployments_url")
    val deploymentsUrl: String? = null,

    @field:JsonProperty("has_projects")
    val hasProjects: Boolean? = null,

    @field:JsonProperty("archived")
    val archived: Boolean? = null,

    @field:JsonProperty("has_wiki")
    val hasWiki: Boolean? = null,

    @field:JsonProperty("updated_at")
    val updatedAt: String? = null,

    @field:JsonProperty("comments_url")
    val commentsUrl: String? = null,

    @field:JsonProperty("stargazers_url")
    val stargazersUrl: String? = null,

    @field:JsonProperty("disabled")
    val disabled: Boolean? = null,

    @field:JsonProperty("git_url")
    val gitUrl: String? = null,

    @field:JsonProperty("has_pages")
    val hasPages: Boolean? = null,

    @field:JsonProperty("owner")
    val owner: Owner? = null,

    @field:JsonProperty("commits_url")
    val commitsUrl: String? = null,

    @field:JsonProperty("compare_url")
    val compareUrl: String? = null,

    @field:JsonProperty("git_commits_url")
    val gitCommitsUrl: String? = null,

    @field:JsonProperty("topics")
    val topics: List<Any?>? = null,

    @field:JsonProperty("blobs_url")
    val blobsUrl: String? = null,

    @field:JsonProperty("git_tags_url")
    val gitTagsUrl: String? = null,

    @field:JsonProperty("merges_url")
    val mergesUrl: String? = null,

    @field:JsonProperty("downloads_url")
    val downloadsUrl: String? = null,

    @field:JsonProperty("has_issues")
    val hasIssues: Boolean? = null,

    @field:JsonProperty("url")
    val url: String? = null,

    @field:JsonProperty("contents_url")
    val contentsUrl: String? = null,

    @field:JsonProperty("mirror_url")
    val mirrorUrl: Any? = null,

    @field:JsonProperty("milestones_url")
    val milestonesUrl: String? = null,

    @field:JsonProperty("teams_url")
    val teamsUrl: String? = null,

    @field:JsonProperty("fork")
    val fork: Boolean? = null,

    @field:JsonProperty("issues_url")
    val issuesUrl: String? = null,

    @field:JsonProperty("events_url")
    val eventsUrl: String? = null,

    @field:JsonProperty("issue_events_url")
    val issueEventsUrl: String? = null,

    @field:JsonProperty("assignees_url")
    val assigneesUrl: String? = null,

    @field:JsonProperty("open_issues")
    val openIssues: Int? = null,

    @field:JsonProperty("watchers_count")
    val watchersCount: Int? = null,

    @field:JsonProperty("node_id")
    val nodeId: String? = null,

    @field:JsonProperty("homepage")
    val homepage: Any? = null,

    @field:JsonProperty("forks_count")
    val forksCount: Int? = null
)
