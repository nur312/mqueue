package ru.fintech.mqueue.entity

import org.hibernate.Hibernate
import ru.fintech.mqueue.dto.EventDto
import javax.persistence.*

enum class Type {
    SMS,
    EMAIL,
    PUSH
}

enum class Status {
    NEW,
    IN_PROCESS,
    DONE,
    ERROR
}

@Entity
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Enumerated(EnumType.STRING)
    val type: Type,
    val body: String,
    @Enumerated(EnumType.STRING)
    var status: Status,
) {
    // Раз уж сама IDEA говорит, что так будет эффективнее, решил так сделать
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Event

        return id != 0L && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , type = $type , body = $body , status = $status )"
    }
}

fun Event.toDto() = EventDto(id, type, body, status)
