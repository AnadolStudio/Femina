package com.anadolstudio.femina.base.navigation.router

import ru.terrakok.cicerone.Router

interface RouterProvider {

    fun getRouter(): Router

}