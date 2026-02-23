package net.petrikainulainen.mockk.user

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

/**
 * Demonstrates how we can create test doubles manually with the
 * [mockk] and immutable class properties.
 */
class UserAccountRegistrationServiceManualClassPropertyTest {

    private val repository = mockk<UserRepository>()
    private val emailService = mockk<EmailService>()
    private val service = UserAccountRegistrationService(repository, emailService)

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