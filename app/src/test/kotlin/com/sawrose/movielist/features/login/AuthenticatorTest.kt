package com.sawrose.movielist.features.login

import com.sawrose.movielist.UnitTest
import org.amshove.kluent.shouldBe
import org.junit.Test

class AuthenticatorTest : UnitTest() {

    private val authenticator = Authenticator()

    @Test
    fun `return default value`() {
        authenticator.userLoggedIn() shouldBe true
    }
}