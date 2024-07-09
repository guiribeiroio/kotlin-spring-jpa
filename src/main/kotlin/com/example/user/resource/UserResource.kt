package com.example.user.resource

import com.example.user.domain.Address
import com.example.user.domain.AddressPK
import com.example.user.domain.Person
import com.example.user.repo.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import javax.persistence.PersistenceException
import javax.transaction.Transactional


@RestController
@Transactional
class UserResource {

    @Autowired
    lateinit var repository: UserRepo

    @GetMapping("/personCreate")
    fun all(): MutableList<Person> {

        val listPerson : MutableList<Person> = arrayListOf()

        for(value : Int in 1..1000) {
            listPerson.add(
                    Person(id = value.toBigInteger(), deleted = null,
                            address = Address( AddressPK(value.toString(),"PPTH"+value),"Rd "+ value)
                    )
            )
        }

        try {
            repository.saveAll(listPerson)
        } catch (e : PersistenceException ) {
            print(e)
        }

        return repository.findAll()
    }

    @GetMapping("/person")
    fun find(): List<Person> {

        return repository.findAll()
    }

    @GetMapping("/person/{road}")
    fun findByRoad(@PathVariable("road") road : String): List<Person> {

        return repository.findPeopleByAddress(Address(AddressPK(houseNumber = "332", postCode = "PPTH332") , road))
    }
}