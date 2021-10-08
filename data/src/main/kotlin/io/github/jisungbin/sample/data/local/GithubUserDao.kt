/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithbUserDao.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:28
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.jisungbin.sample.data.local.entity.GithubUserEntity
import io.github.jisungbin.sample.data.local.entity.GithubUserInformationEntity

@Dao
interface GithubUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(githubUser: List<GithubUserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInformation(githubUserInformation: GithubUserInformationEntity)

    @Query("SELECT * FROM GithubUserEntity WHERE searchKeyword LIKE :searchKeyword")
    suspend fun getUsers(searchKeyword: String): List<GithubUserEntity>

    @Query("SELECT * FROM GithubUserInformationEntity WHERE loginId LIKE :loginId")
    suspend fun getUserInformation(loginId: String): GithubUserInformationEntity
}
