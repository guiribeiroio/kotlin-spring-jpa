package com.example.user.domain

import lombok.Builder
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
@Builder
class Address( @EmbeddedId var pk:  AddressPK, var line_1: String)
