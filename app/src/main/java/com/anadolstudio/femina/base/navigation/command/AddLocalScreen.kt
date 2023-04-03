package com.anadolstudio.femina.base.navigation.command

import ru.terrakok.cicerone.commands.Command

class AddLocalScreen(
        val rootScreenKey: String,
        val localScreenKey: String,
        val localScreenData: Any? = null
) : Command
