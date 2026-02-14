package net.petrikainulainen.mockk.user

/**
 * Declares function used to communicaate with the database.
 */
interface UserRepository {

    /**
     * Checks if the database contains a user account that has the given email address.
     * @param email The email address
     * @return  [true] if the user account exists and [false] otherwise.
     */
    fun existsByEmail(email: String): Boolean

    /**
     * Saves a new account the database.
     * @param input The information of the new user account.
     * @return The information of the saved user account.
     */
    fun save(input: RegisterUserAccountRequest): UserAccount
}