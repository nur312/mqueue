package ru.fintech.mqueue.producer

import org.springframework.jms.JmsException
import org.springframework.jms.core.JmsTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.fintech.mqueue.config.ActiveMQConfig
import ru.fintech.mqueue.entity.Event
import ru.fintech.mqueue.entity.toDto
import ru.fintech.mqueue.service.EventService
import java.util.concurrent.TimeUnit

@Component
class EventProducer(
    private val jmsTemplate: JmsTemplate,
    private val eventService: EventService
) {

    // Для тестирования поставил 5 секунд
    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    //    @Scheduled(cron = "@hourly")
    fun run() {

        eventService.findNewEvents().forEach(this::sendEventToMq)
    }

    private fun sendEventToMq(event: Event): Event {
        return try {

            jmsTemplate.convertAndSend(ActiveMQConfig.ORDER_QUEUE, event.toDto())

            eventService.setInProcessAndSave(event)
        } catch (_: JmsException) {

            eventService.setErrorAndSave(event)
        }

    }
}