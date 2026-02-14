package net.petrikainulainen.mockk.user

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Demonstrates how we can create test doubles automatically by using the
 * [MockK] annotation and the [MockKExtension] JUnit Jupiter extension.
 */
@ExtendWith(MockKExtension::class)
class UserAccountRegistrationServiceExtensionTest {

    @MockK
    private lateinit var repository: UserRepository

    @MockK
    private lateinit var emailService: EmailService

    @InjectMockKs
    private lateinit var service: UserAccountRegistrationService

    @Test
    fun `should throw exception when the registered user account doesn't have unique email`() {
        every { repository.existsByEmail(any()) } returns true

        assertThatThrownBy {
            service.registerUserAccount(RegisterUserAccountRequest(
                email = "john.doe@gmail.com",
                name = "John Doe"
            ))
        }.isExactlyInstanceOf(UserAccountExistsException::class.java)
    }
}