package com.example.user.repo

import com.example.user.domain.Address
import com.example.user.domain.AddressPK
import com.example.user.domain.Person
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigInteger

@RunWith(SpringRunner::class)
@DataJpaTest
@ActiveProfiles("test")
class UserRepoTest {


    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var repository: UserRepo

    lateinit var stubPerson: Person

    @Before
    fun setUp(){
        stubPerson =  Person(id = BigInteger.ONE, deleted = null, address = Address( AddressPK("5","PPTHH3"),"Rd 2"))
    }

    @Test
    fun userPersist() {
        assertThat(repository.save(stubPerson), `is`(stubPerson))
    }

    @Test
    fun userFind() {
        repository.save(stubPerson)
        assertThat(repository.findById(BigInteger.ONE).get() , `is`(stubPerson))
    }

    @Test
    fun userDelete() {
        repository.save(stubPerson)
        val personDeleted : Person = repository.findById(BigInteger.ONE).get()
        repository.save(personDeleted.delete())
        assertThat(repository.findById(BigInteger.ONE).get() , `is`(stubPerson))
    }

    @Test
    fun shouldCreateAddress(){
        assertThat(repository.save(stubPerson)
        , `is`(stubPerson))
    }

}