package me.tryfle.shortcuts.utils

import net.weavemc.loader.api.command.Command
import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText
import net.minecraft.util.EnumChatFormatting
import net.weavemc.loader.api.command.CommandBus
import net.weavemc.loader.api.event.SubscribeEvent
import net.weavemc.loader.api.event.TickEvent

class Handler {
    @Suppress("UNUSED_PARAMETER", "UNUSED")
    @SubscribeEvent
    fun insanity(e: TickEvent) {
        val player = Minecraft.getMinecraft().thePlayer
        val shortenedCommand = Config.shortenedCommand
        val commandToRun = Config.commandToRun
        val extraArgs = Config.extraArgs
        if (player != null && Config.shouldRegister && !Config.longCmd) {
                println("[SC] Registered command: /$commandToRun as /$shortenedCommand")
            player.addChatMessage(ChatComponentText(EnumChatFormatting.GREEN.toString() + "Registered command /$commandToRun as /$shortenedCommand!"))
            CommandBus.register(object : Command(shortenedCommand) {
                override fun handle(newargs: Array<out String>) {
                    player.sendChatMessage("/$commandToRun")
                }
            })
            Config.shouldRegister = false
        }
        if (player != null && Config.longCmd && Config.shouldRegister) {
            println("[SC] Registered command: /$commandToRun as /$shortenedCommand")
            player.addChatMessage(ChatComponentText(EnumChatFormatting.GREEN.toString() + "Registered command /$shortenedCommand as /$commandToRun $extraArgs!"))
            CommandBus.register(object : Command(shortenedCommand) {
                override fun handle(newargs: Array<out String>) {
                    player.sendChatMessage("/$commandToRun $extraArgs")
                }
            })
            Config.longCmd = false
            Config.shouldRegister = false
        }
    }
}