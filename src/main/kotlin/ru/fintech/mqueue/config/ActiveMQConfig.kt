package ru.fintech.mqueue.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.config.JmsListenerContainerFactory
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.jms.support.converter.MessageType
import org.springframework.scheduling.annotation.EnableScheduling

@EnableJms
@EnableScheduling
@Configuration
class ActiveMQConfig {
    @Bean
    fun queueListenerFactory(): JmsListenerContainerFactory<*> {
        val factory = DefaultJmsListenerContainerFactory()
        factory.setMessageConverter(messageConverter())
        return factory
    }

    @Bean
    fun messageConverter(): MessageConverter {
        val converter = MappingJackson2MessageConverter()
        converter.setTargetType(MessageType.TEXT)
        converter.setTypeIdPropertyName("_type")
        return converter
    }

    companion object {
        // Не вынес в application.yaml файл, так как это значение нужно указывать в аннотациях, а там значения
        // должны быть известны в момент компиляции.
        const val ORDER_QUEUE = "order-queue"
    }
}