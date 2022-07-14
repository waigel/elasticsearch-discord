package me.waigel.backend

import org.elasticsearch.client.RestHighLevelClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.RestClients
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@Configuration
@EnableElasticsearchRepositories(basePackages = ["me.waigel.backend.repositories"])
class Config(
    @Value("\${elasticsearch.host}")
    val host: String,
    @Value("\${elasticsearch.port}")
    val port: Int,
    @Value("\${elasticsearch.username}")
    val username: String,
    @Value("\${elasticsearch.password}")
    val password: String

) {

    @Bean
    fun client(): RestHighLevelClient {
        val clientConfiguration = ClientConfiguration.builder()
            .connectedTo("$host:$port")
            .usingSsl()
            .withBasicAuth(username, password)
            .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    fun elasticsearchTemplate(): ElasticsearchOperations {
        return ElasticsearchRestTemplate(client());
    }
}