/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserRepositoryDao.kt] created by Ji Sungbin on 21. 10. 9. 오후 4:52
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.jisungbin.githubusers.data.local.entity.GithubUserRepositoryEntity

@Dao
internal interface GithubUserRepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(githubUserRepositories: List<GithubUserRepositoryEntity>)

    @Query("SELECT * FROM GithubUserRepositoryEntity WHERE ownerLoginId LIKE :ownerLoginId")
    suspend fun loadAllFromOwnerLoginId(ownerLoginId: String): List<GithubUserRepositoryEntity>
}
