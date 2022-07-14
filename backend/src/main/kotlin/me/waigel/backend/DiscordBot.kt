package me.waigel.backend

import me.waigel.backend.events.OnMessageEvent
import me.waigel.backend.repositories.DiscordMessageRepository
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DiscordBot(
    @Value("\${discord.token}")
    private val token: String,
    @Autowired
    private val discordMessageRepository: DiscordMessageRepository
) {
    private var discord: JDA = JDABuilder.create(token, GatewayIntent.GUILD_MESSAGES).build();

    init {
        discord.addEventListener(OnMessageEvent(discordMessageRepository))
    }

}