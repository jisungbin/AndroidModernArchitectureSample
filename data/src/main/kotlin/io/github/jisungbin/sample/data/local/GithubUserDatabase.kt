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
import io.github.jisungbin.sample.data.local.entity.GithubUserEntity
import io.github.jisungbin.sample.data.local.entity.GithubUserInformationEntity

@Database(version = 1, entities = [GithubUserEntity::class, GithubUserInformationEntity::class])
abstract class GithubUserDatabase : RoomDatabase() {
    abstract val dao: GithubUserDao

    companion object {
        fun build(context: Context) = synchronized(GithubUserDatabase::class.java) {
            Room.databaseBuilder(context, GithubUserDatabase::class.java, "github-user.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
