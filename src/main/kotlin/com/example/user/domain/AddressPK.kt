package com.example.user.domain

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class AddressPK(private val houseNumber: String, private val postCode: String) : Serializable
