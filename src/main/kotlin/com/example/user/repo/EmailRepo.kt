package com.example.user.repo;

import com.example.user.domain.Email
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.math.BigInteger

@Repository
interface EmailRepo : JpaRepository<Email, BigInteger> {}