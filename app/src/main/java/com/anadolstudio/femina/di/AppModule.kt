package com.anadolstudio.femina.di

import dagger.Module

@Module(includes = [RepositoryModule::class, UseCaseModule::class])
class AppModule
