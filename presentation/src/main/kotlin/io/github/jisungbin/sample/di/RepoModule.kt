/*
 * GitMessengerBot © 2021 지성빈 & 구환. all rights reserved.
 * GitMessengerBot license is under the GPL-3.0.
 *
 * [RepositoryModule.kt] created by Ji Sungbin on 21. 8. 30. 오후 5:58
 *
 * Please see: https://github.com/GitMessengerBot/GitMessengerBot-Android/blob/master/LICENSE.
 */

package io.github.jisungbin.sample.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import io.github.jisungbin.sample.data.repo.GithubUserRepoImpl
import io.github.jisungbin.sample.domain.repo.GithubUserRepo
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {
    @Provides
    @ViewModelScoped
    fun provideGithubUserRepo(
        @ApplicationContext context: Context,
        retrofit: Retrofit
    ): GithubUserRepo = GithubUserRepoImpl(context, retrofit)
}
