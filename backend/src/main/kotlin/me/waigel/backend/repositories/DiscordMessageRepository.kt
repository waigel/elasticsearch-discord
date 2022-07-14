package me.waigel.backend.repositories

import me.waigel.backend.models.DiscordMessage
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscordMessageRepository : ElasticsearchRepository<DiscordMessage, String?> {
}