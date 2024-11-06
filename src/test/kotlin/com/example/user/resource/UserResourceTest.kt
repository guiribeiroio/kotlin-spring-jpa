package com.example.user.resource

import com.example.user.domain.Email
import com.example.user.repo.EmailRepo
import com.example.user.repo.UserRepo
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@WebMvcTest
class UserResourceTest {

    @Autowired lateinit var mockMvc: MockMvc

    private val userRepo: UserRepo = mockk()
    private val emailRepo: EmailRepo = mockk()
    private val userResource = UserResource(userRepo, emailRepo)


    @Test
    fun whenGetListEmailSubscribed_thenReturnEmail(){

        val emailList: List<Email> = listOf(Email("email_1"), Email("email_2"))

        //given
        every { emailRepo.findAll() } returns emailList

        //then
        mockMvc.perform(get("/email"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.email").value("email_1"))

    }

    @Test
    fun whenPostEmailSubscribe_thenReturnCreatedEmail(){

        val emailInput = "{\"email\": \"email@email.com\"}"
        val email = Email(email = "email@email.com\\")

        //given
        every { emailRepo.save<Email>(any())} returns email

        //when
        var result = userResource.subscribeEmail(emailInput)

        //then

    }

    @Test
    fun whenInvalidPostEmailSubscribe_thenReturnError(){

        val emailInput = "{\"email\": \"email\"}"
        val email = Email(email = "email")

        //given
        every { emailRepo.save<Email>(any())} returns email

        //when
        var result = userResource.subscribeEmail(emailInput)

        //then

    }
}
