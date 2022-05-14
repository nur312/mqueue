package ru.fintech.mqueue.service.communication

import org.springframework.stereotype.Service

@Service("PUSH_SERVICE")
class PushService : CommunicationService {

    override fun sendMessage(message: String) {

        println("push: $message")
    }
}