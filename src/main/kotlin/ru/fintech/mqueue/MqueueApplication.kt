package ru.fintech.mqueue

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MqueueApplication

fun main(args: Array<String>) {
	runApplication<MqueueApplication>(*args)
}
