package ru.fintech.mqueue.dto

import ru.fintech.mqueue.entity.Event
import ru.fintech.mqueue.entity.Status
import ru.fintech.mqueue.entity.Type

// Создал DTO, так как мне показалось, что mq в большинстве случаев используется между сервисами
data class EventDto(
    val id: Long = 0,
    val type: Type = Type.EMAIL,
    val body: String = "",
    val status: Status = Status.NEW,
)

fun EventDto.toEntity() = Event(id, type, body, status)