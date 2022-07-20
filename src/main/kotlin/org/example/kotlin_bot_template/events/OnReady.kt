package org.example.kotlin_bot_template.events

import net.dv8tion.jda.api.events.ReadyEvent
import org.example.kotlin_bot_template.Logger

class OnReady {
    fun handle(event: ReadyEvent) {
        Logger.info("Bot is ready! Logged as ${event.jda.selfUser.name}")
    }
}
