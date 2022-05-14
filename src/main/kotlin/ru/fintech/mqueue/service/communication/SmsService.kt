package ru.fintech.mqueue.service.communication

import org.springframework.stereotype.Service

@Service("SMS_SERVICE")
class SmsService : CommunicationService {

    override fun sendMessage(message: String) {

        println("sms: $message")
    }
}