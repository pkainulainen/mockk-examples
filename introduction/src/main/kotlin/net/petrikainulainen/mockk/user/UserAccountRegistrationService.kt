package net.petrikainulainen.mockk.user

/**
 *  Provides support for registering new user accounts.
 */
class UserAccountRegistrationService(
    private val repository: UserRepository,
    private val emailService: EmailService
) {

    /**
     * Registers a new user account.
     * @param input The information of the new user account.
     * @return The information of the registered user account.
     * @throws UserAccountExistsException if the registered user account doesn't have a unique email address.
     */
    fun registerUserAccount(input: RegisterUserAccountRequest): UserAccount {
        if (repository.existsByEmail(input.email)) {
            throw UserAccountExistsException(input.email)
        }

        val registeredUserAccount = repository.save(input)
        emailService.sendWelcomeEmail(registeredUserAccount.email)

        return registeredUserAccount
    }
}