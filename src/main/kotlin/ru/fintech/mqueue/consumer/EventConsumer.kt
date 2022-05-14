package ru.fintech.mqueue.consumer

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.jms.annotation.JmsListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import ru.fintech.mqueue.config.ActiveMQConfig.Companion.ORDER_QUEUE
import ru.fintech.mqueue.dto.EventDto
import ru.fintech.mqueue.dto.toEntity
import ru.fintech.mqueue.service.EventService
import ru.fintech.mqueue.service.communication.CommunicationService

@Component
class EventConsumer(
    private val eventService: EventService,
    private val context: ApplicationContext
) {

    @JmsListener(destination = ORDER_QUEUE)
    fun receiveMessage(
        @Payload event: EventDto
    ) {

        try {
            sendMessage(event)
            eventService.setDoneAndSave(event.toEntity())

        } catch (_: BeansException) {

            eventService.setErrorAndSave(event.toEntity())
        }
    }

    private fun sendMessage(event: EventDto) {

        val communicationService = context.getBean("${event.type.name}_SERVICE") as CommunicationService
        communicationService.sendMessage(event.body)
    }

}