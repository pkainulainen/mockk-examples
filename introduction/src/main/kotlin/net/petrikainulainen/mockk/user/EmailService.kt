package net.petrikainulainen.mockk.user

/**
 * declares the functions used to send email messages to the users of
 * this application.
 */
interface EmailService {

    /**
     * Sends a welcome email to a new user.
     * @param email The email address of the user.
     */
    fun sendWelcomeEmail(email: String)
}