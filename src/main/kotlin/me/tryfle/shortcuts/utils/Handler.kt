package me.tryfle.shortcuts.utils

import net.weavemc.loader.api.command.Command
import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText
import net.minecraft.util.EnumChatFormatting
import net.weavemc.loader.api.command.CommandBus
import net.weavemc.loader.api.event.SubscribeEvent
import net.weavemc.loader.api.event.TickEvent

class Handler {
    @SubscribeEvent
    fun insanity(e: TickEvent) {
        val player = Minecraft.getMinecraft().thePlayer
        val shortenedCommand = Config.shortenedCommand
        val commandToRun = Config.commandToRun
        if (player != null) {
            if (Config.shouldRegister) {
                println("[SC] Registered command: /$commandToRun as /$shortenedCommand")
                player.addChatMessage(ChatComponentText(EnumChatFormatting.GREEN.toString() + "Registered command /$commandToRun as /$shortenedCommand!"))
                CommandBus.register(object : Command(shortenedCommand) {
                    override fun handle(newargs: Array<out String>) {
                        player.sendChatMessage("/$commandToRun")
                    }
                })
                Config.shouldRegister = false
            }
        }
    }
}