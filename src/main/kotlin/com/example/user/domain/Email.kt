package com.example.user.domain

import lombok.Builder
import java.math.BigInteger
import java.util.*
import javax.persistence.*

//class Author(var type: String? = null, var name: String) {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0
//    @CreationTimestamp val createdAt: TimeStamp = TimeStamp(0)
//}

@Entity
@Builder
data class Email(
    var email: String
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(columnDefinition = "BIGINT") var id: BigInteger = BigInteger.ZERO
    @Temporal(TemporalType.DATE) var createdAt: Date = Date()
    @Temporal(TemporalType.DATE) var deleted: Date? = null

    override fun hashCode(): Int = id.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Email

        if (id != other.id) return false

        return true
    }


    //fun delete(): Email = Email(id, Date.from(Instant.now()))

    //constructor(email: String, createdAt: Nothing?, deleted: Nothing? ) : this(BigInteger(1), email, createdAt, deleted) {}
}
