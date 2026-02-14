package net.petrikainulainen.mockk.user

/**
 * Contains the information required to register a new user account.
 */
data class RegisterUserAccountRequest(val email: String, val name: String)

/**
 * Contains the information of a registered user account.
 */
data class UserAccount(val id: Long, val email: String, val name: String)

/**
 * This exception is thrown if the registered user account doesn't
 * have a unique email address.
 */
class UserAccountExistsException(email: String) :
    RuntimeException("The user account with email address: $email exists")