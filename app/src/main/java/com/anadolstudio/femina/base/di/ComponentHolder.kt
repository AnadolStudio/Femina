package com.anadolstudio.femina.base.di

interface ComponentHolder<out Component> {

    fun provideComponent(): Component

    fun onDependencyReleased()
}