package com.anadolstudio.femina.utils.viewmodel

sealed class BaseViewState<Data> {

    class Loading<Data> : BaseViewState<Data>()

    class Content<Data>(val content: Data) : BaseViewState<Data>()

    class Stub<Data> : BaseViewState<Data>()
}
