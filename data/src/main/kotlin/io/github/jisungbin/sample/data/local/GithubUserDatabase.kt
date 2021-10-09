/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserDatabase.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:24
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.jisungbin.sample.data.local.dao.GithubUserDao
import io.github.jisungbin.sample.data.local.dao.GithubUserEventDao
import io.github.jisungbin.sample.data.local.dao.GithubUserInformationDao
import io.github.jisungbin.sample.data.local.dao.GithubUserRepositoryDao
import io.github.jisungbin.sample.data.local.entity.GithubUserEntity
import io.github.jisungbin.sample.data.local.entity.GithubUserEventEntity
import io.github.jisungbin.sample.data.local.entity.GithubUserInformationEntity
import io.github.jisungbin.sample.data.local.entity.GithubUserRepositoryEntity

@Database(
    version = 3,
    entities = [
        GithubUserEntity::class,
        GithubUserEventEntity::class,
        GithubUserInformationEntity::class,
        GithubUserRepositoryEntity::class
    ]
)
internal abstract class GithubUserDatabase : RoomDatabase() {
    abstract val userDao: GithubUserDao
    abstract val userEventDao: GithubUserEventDao
    abstract val userInformationDao: GithubUserInformationDao
    abstract val userRepositoryDao: GithubUserRepositoryDao

    internal companion object {
        fun build(context: Context) = synchronized(GithubUserDatabase::class.java) {
            Room.databaseBuilder(context, GithubUserDatabase::class.java, "github-user.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
