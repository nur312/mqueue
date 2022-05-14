package ru.fintech.mqueue.service.communication

interface CommunicationService {
    fun sendMessage(message: String)
}