package com.example.user.resource

import com.example.user.domain.Address
import com.example.user.domain.AddressPK
import com.example.user.domain.Person
import com.example.user.domain.Email
import com.example.user.dto.EmailDto

import com.example.user.repo.UserRepo
import com.example.user.repo.EmailRepo
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.persistence.PersistenceException
import javax.transaction.Transactional

@RestController
@Transactional
@CrossOrigin(origins = ["http://localhost:3000"])
class UserResource {

    @Autowired
    lateinit var userRepo: UserRepo

    @Autowired
    lateinit var emailRepo: EmailRepo

    val mapper = jacksonObjectMapper()

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
            userRepo.saveAll(listPerson)
        } catch (e : PersistenceException ) {
            print(e)
        }

        return userRepo.findAll()
    }

    @GetMapping("/person")
    fun find(): List<Person> {
        return userRepo.findAll()
    }

    @GetMapping("/person/{road}")
    fun findByRoad(@PathVariable("road") road : String): List<Person> {
        return userRepo.findPeopleByAddress(Address(AddressPK(houseNumber = "332", postCode = "PPTH332") , road))
    }

    @GetMapping("/email")
    fun emailSubscribe(): List<Email> {
        return emailRepo.findAll()
    }

    @PostMapping("/email")
    fun emailSubscribe(@RequestBody email : String): Email {
        val emailDto: EmailDto = mapper.readValue<EmailDto>(email)
        return emailRepo.save(Email(email = emailDto.email))
    }
}
