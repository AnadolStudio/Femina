package com.anadolstudio.femina.extension

fun <T> lazyUnsafe(initializer: () -> T): Lazy<T> =
        lazy(mode = LazyThreadSafetyMode.NONE, initializer = initializer)
