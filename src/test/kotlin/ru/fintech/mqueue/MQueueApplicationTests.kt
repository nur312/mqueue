package ru.fintech.mqueue

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.fintech.mqueue.dao.EventDao
import ru.fintech.mqueue.entity.Event
import ru.fintech.mqueue.entity.Status
import ru.fintech.mqueue.entity.Type

@Suppress("NonAsciiCharacters")
@SpringBootTest
class MQueueApplicationTests(@Autowired private val eventDao: EventDao) {

	@Test
	fun `Проверка главного сценария`() {

		val event = Event(0, Type.EMAIL, "Hello World", Status.NEW)
		val id = eventDao.save(event).id

		Thread.sleep(11 * 1000)

		val savedEvent = eventDao.getById(id)

		assertEquals(Status.DONE, savedEvent.status)
	}

}
