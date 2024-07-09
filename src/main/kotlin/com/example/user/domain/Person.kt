package com.example.user.domain

import lombok.Builder
import java.math.BigInteger
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Builder
class Person(@Id var id: BigInteger, @Temporal(TemporalType.DATE) var deleted : Date?) {

    @OneToMany(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    var address: MutableList<Address> = arrayListOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun delete(): Person {
        return Person(id, Date.from(Instant.now()))
    }

    constructor(id: BigInteger, deleted: Nothing?, address: Address ) : this(id,deleted) {this.address.add(address)}

}