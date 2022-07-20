package org.example.kotlin_bot_template

import dev.minn.jda.ktx.events.CoroutineEventListener
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.ReadyEvent
import org.example.kotlin_bot_template.events.OnReady

class EventHandler : CoroutineEventListener {
    override suspend fun onEvent(event: GenericEvent) {
        when (event) {
            is ReadyEvent -> {
                OnReady().handle(event)
            }
        }
    }

}
