package com.gacd.iainteractive.ui.auth

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val emptyValues: Int? = null,
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)