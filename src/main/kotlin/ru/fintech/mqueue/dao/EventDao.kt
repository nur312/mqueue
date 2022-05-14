package ru.fintech.mqueue.dao

import org.springframework.data.jpa.repository.JpaRepository
import ru.fintech.mqueue.entity.Event
import ru.fintech.mqueue.entity.Status

interface EventDao : JpaRepository<Event, Long> {
    fun findByStatus(status: Status): List<Event>
}