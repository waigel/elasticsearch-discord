package me.waigel.backend.models

import net.dv8tion.jda.api.entities.Message
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "discord_message")
class DiscordMessage(
    @Id
    var messageId: String = "",
    var channelId: String? = null,
    var guildId: String? = null,
    var authorId: String? = null,
    var content: String? = null,
    var timestamp: Long? = null,
    var edited: Boolean? = null,
    var type: String? = null,
    var attachments: List<String>? = null,
    var embeds: List<String>? = null,
    var mentionsUserIds: List<String>? = null,
    var webhook: Boolean? = null,
    var pinned: Boolean? = null,
    var jumpUrl: String? = null,
){

    fun from(message: Message): DiscordMessage {
        message.embeds.map { it .t }
        this.messageId = message.id
        this.channelId = message.channel.id
        this.guildId = message.guild.id
        this.authorId = message.author.id
        this.content = message.contentRaw
        this.timestamp = message.timeCreated.toEpochSecond()
        this.edited = message.isEdited
        this.type = message.type.name
        this.attachments = message.attachments.map { it.url }
        this.embeds = message.embeds.map { it.toString() }
        this.mentionsUserIds = message.mentions.users.map { it.id }
        this.webhook = message.isWebhookMessage
        this.pinned = message.isPinned
        this.jumpUrl = message.jumpUrl
        return this;
    }
}