/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserEvents.kt] created by Ji Sungbin on 21. 10. 9. 오후 4:02
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.domain.model.event

@JvmInline
value class GithubUserEvents(val items: List<GithubUserEventItem>)
