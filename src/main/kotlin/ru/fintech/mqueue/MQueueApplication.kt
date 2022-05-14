package ru.fintech.mqueue

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MQueueApplication

fun main(args: Array<String>) {

	runApplication<MQueueApplication>(*args)
}
