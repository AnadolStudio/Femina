package com.anadolstudio.femina.base.di

@Suppress("unused")
abstract class SingleComponentHolder<in ParentComponent, out Component>(
        private val parentComponentHolder: ComponentHolder<ParentComponent>
) : BaseComponentHolder<Component>() {

    protected abstract fun provideInternal(parentComponent: ParentComponent): Component

    override fun getComponent(): Component = provideInternal(parentComponentHolder.provideComponent())

    @Suppress("MemberVisibilityCanPrivate")
    override fun onComponentDestroyed() {
        parentComponentHolder.onDependencyReleased()
    }
}