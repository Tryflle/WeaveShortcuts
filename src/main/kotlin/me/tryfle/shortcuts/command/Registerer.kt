package me.tryfle.shortcuts.command

import net.weavemc.loader.api.command.Command
import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText
import net.minecraft.util.EnumChatFormatting
import me.tryfle.shortcuts.utils.Config
import me.tryfle.shortcuts.utils.Handler
import net.weavemc.loader.api.event.EventBus

class Registerer: Command("register") {
    override fun handle(args: Array<out String>) {
        val player = Minecraft.getMinecraft().thePlayer
        val c = Config
        if (args.size != 2 && player != null) {
            println("[SC] Registration failure: Invalid arguments.")
            player.addChatMessage(ChatComponentText(EnumChatFormatting.RED.toString() + "Invalid arguments!"))
            player.addChatMessage(ChatComponentText(EnumChatFormatting.RED.toString() + "Usage: /register <shortened-command> <command-to-run>"))
            return
        } else {
            if (player != null) {
                c.commandToRun = args[1]
                c.shortenedCommand = args[0]
                c.shouldRegister = true
                EventBus.subscribe(Handler())
            }
        }
    }
}