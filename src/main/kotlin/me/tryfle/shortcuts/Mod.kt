package me.tryfle.shortcuts

import me.tryfle.shortcuts.command.Registerer
import me.tryfle.shortcuts.utils.Config
import net.weavemc.loader.api.ModInitializer
import net.weavemc.loader.api.command.CommandBus

class Mod: ModInitializer {

    override fun preInit() {
        println("[SC] Pre-Init phase")
        CommandBus.register(Registerer())
        Config.shouldRegister = false
        Config.longCmd = false
    }
}