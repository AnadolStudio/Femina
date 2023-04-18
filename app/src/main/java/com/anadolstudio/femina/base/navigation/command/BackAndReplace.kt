package com.anadolstudio.femina.base.navigation.command

import ru.terrakok.cicerone.commands.Command

class BackAndReplace(
        val backScreenKey: String,
        val replaceScreenKey: String,
        val replaceTransactionData: Any?
) : Command