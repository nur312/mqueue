package ru.fintech.mqueue.service

import org.springframework.stereotype.Service
import ru.fintech.mqueue.dao.EventDao
import ru.fintech.mqueue.entity.Event
import ru.fintech.mqueue.entity.Status

@Service
class EventService(
    private val eventDao: EventDao
) {

    fun findNewEvents(): List<Event> {

        return eventDao.findByStatus(Status.NEW)
    }

    fun setInProcessAndSave(event: Event): Event {
        event.status = Status.IN_PROCESS
        return eventDao.save(event)
    }

    fun setDoneAndSave(event: Event): Event {
        event.status = Status.DONE
        return eventDao.save(event)
    }

    fun setErrorAndSave(event: Event): Event {
        event.status = Status.ERROR
        return eventDao.save(event)
    }
}