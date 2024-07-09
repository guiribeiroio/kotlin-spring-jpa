package com.example.user.repo;

import com.example.user.domain.Address
import com.example.user.domain.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.math.BigInteger

@Repository
interface UserRepo : JpaRepository<Person, BigInteger> {

    //@Override
    fun findPeopleByAddress(address: Address) : List<Person>
}
