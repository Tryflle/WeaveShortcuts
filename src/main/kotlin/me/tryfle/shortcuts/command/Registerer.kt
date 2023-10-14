package me.tryfle.shortcuts.command

import net.weavemc.loader.api.command.Command
import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText
import net.minecraft.util.EnumChatFormatting
import me.tryfle.shortcuts.utils.Config
import me.tryfle.shortcuts.utils.Handler
import net.weavemc.loader.api.event.EventBus
//truly one of the tryfle code moments of all time

class Registerer: Command("register") {
    override fun handle(args: Array<out String>) {
        val player = Minecraft.getMinecraft().thePlayer
        val c = Config
        if (player != null) {
            when (args.size) {
                2 -> {
                    c.commandToRun = args[1]
                    c.shortenedCommand = args[0]
                    c.shouldRegister = true
                    EventBus.subscribe(Handler())
                }
                3 -> {
                    c.commandToRun = args[1]
                    c.shortenedCommand = args[0]
                    c.shouldRegister = true
                    c.extraArgs = args[2]
                    c.longCmd = true
                    EventBus.subscribe(Handler())
                }
                4 -> {
                    c.commandToRun = args[1]
                    c.shortenedCommand = args[0]
                    c.shouldRegister = true
                    c.extraArgs = args[2] + " " + args[3]
                    c.longCmd = true
                    EventBus.subscribe(Handler())
                }
                else -> {
                    if (player != null) {
                        println("[SC] Registration failure: Invalid arguments.")
                        player.addChatMessage(ChatComponentText(EnumChatFormatting.RED.toString() + "Invalid arguments! (Max args 3)"))
                        player.addChatMessage(ChatComponentText(EnumChatFormatting.RED.toString() + "Usage: /register <shortened-command> <command-to-run>"))
                        return
                    }
                }
            }
        }
    }
}