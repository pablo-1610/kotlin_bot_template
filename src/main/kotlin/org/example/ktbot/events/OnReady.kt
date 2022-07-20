package org.example.ktbot.events

import net.dv8tion.jda.api.events.ReadyEvent
import org.example.ktbot.Logger

class OnReady {
    fun handle(event: ReadyEvent) {
        Logger.info("Bot is ready! Logged as ${event.jda.selfUser.name}")
    }
}
