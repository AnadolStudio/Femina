package com.anadolstudio.femina.base.navigation.router

import com.anadolstudio.femina.base.navigation.command.AddLocalScreen
import com.anadolstudio.femina.base.navigation.command.ReplaceAndReset
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

class BottomNavigationRouter : Router() {

    fun replaceAndResetScreen(screenKey: String, data: Any? = null) {
        executeCommands(ReplaceAndReset(screenKey, data))
    }

    fun newScreenChain(vararg screenChain: Pair<String, Any?>) {

        val commandChain = screenChain.mapIndexed { index, (key, args) ->
            if (index == 0) Replace(key, args) else Forward(key, args)
        }.toTypedArray()

        executeCommands(*commandChain)
    }

    fun addLocalScreen(rootScreenKey: String, localScreenKey: String, localScreenData: Any? = null) {
        executeCommands(
                ReplaceAndReset(rootScreenKey, null),
                AddLocalScreen(rootScreenKey, localScreenKey, localScreenData)
        )
    }

}