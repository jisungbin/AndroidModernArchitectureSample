/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [UseCaseModule.kt] created by Ji Sungbin on 21. 10. 8. 오후 8:20
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.github.jisungbin.sample.domain.repo.GithubUserRepo
import io.github.jisungbin.sample.domain.usecase.GithubUserSearchUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideGithubUserSearchUseCase(repo: GithubUserRepo) = GithubUserSearchUseCase(repo)

    /*@Provides
    @ViewModelScoped
    fun provideGithubUserInformationUseCase(repo: GithubUserRepo) = GithubUserInformationUseCase(repo)*/
}