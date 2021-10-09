/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserDao.kt] created by Ji Sungbin on 21. 10. 9. 오후 4:53
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.jisungbin.githubusers.data.local.entity.GithubUserEntity

@Dao
internal interface GithubUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(githubUsers: List<GithubUserEntity>)

    @Query("SELECT * FROM GithubUserEntity WHERE searchKeyword LIKE :searchKeyword")
    suspend fun loadAllFromSearchKeyword(searchKeyword: String): List<GithubUserEntity>
}
