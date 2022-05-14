package ru.fintech.mqueue.service.communication

import org.springframework.stereotype.Service

@Service("EMAIL_SERVICE")
class EmailService : CommunicationService {

    override fun sendMessage(message: String) {

        println("email: $message")
    }
}