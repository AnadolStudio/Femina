package com.anadolstudio.femina.base.di

@Suppress("unused")
abstract class RootComponentHolder<out Component> : BaseComponentHolder<Component>() {

    protected abstract fun provideInternal(): Component

    override fun getComponent(): Component = provideInternal()
}