package me.waigel.backend.events

import me.waigel.backend.models.DiscordMessage
import me.waigel.backend.repositories.DiscordMessageRepository
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class OnMessageEvent(
    private val discordMessageRepository: DiscordMessageRepository
): ListenerAdapter() {

    override fun onMessageReceived (event: MessageReceivedEvent) {
        println("Message received: ${event.message}")
        val discordMessage = DiscordMessage().from(event.message);
        discordMessageRepository.save(discordMessage)
    }


}