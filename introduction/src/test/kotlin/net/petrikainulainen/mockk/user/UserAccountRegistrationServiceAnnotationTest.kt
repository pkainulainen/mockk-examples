package net.petrikainulainen.mockk.user

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Demonstrates how we can create test doubles by using the [MockK] annotation
 * and the [MockKAnnotations.init] function.
 */
class UserAccountRegistrationServiceAnnotationTest {

    @MockK
    private lateinit var repository: UserRepository

    @MockK
    private lateinit var emailService: EmailService

    private lateinit var service: UserAccountRegistrationService

    @BeforeEach
    fun configureSystemUnderTest() {
        MockKAnnotations.init(this)
        service = UserAccountRegistrationService(repository, emailService)
    }

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