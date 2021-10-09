/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserEventDao.kt] created by Ji Sungbin on 21. 10. 9. 오후 4:53
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.jisungbin.githubusers.data.local.entity.GithubUserEventEntity

@Dao
internal interface GithubUserEventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(githubUserEvents: List<GithubUserEventEntity>)

    @Query("SELECT * FROM GithubUserEventEntity WHERE loginId LIKE :loginId")
    suspend fun loadAllFromLoginId(loginId: String): List<GithubUserEventEntity>
}
