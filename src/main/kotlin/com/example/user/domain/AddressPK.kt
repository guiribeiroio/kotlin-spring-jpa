package com.example.user.domain

import javax.persistence.Column
import javax.persistence.Embeddable
import java.io.Serializable

@Embeddable
class AddressPK(private val houseNumber: String, private val postCode: String) : Serializable
